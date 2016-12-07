/**
 * 
 */
package com.sagaranand.website.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.sagaranand.website.client.GenericClient;
import com.sagaranand.website.constants.ApiEndpoints;
import com.sagaranand.website.constants.ErrorCodes;
import com.sagaranand.website.constants.ErrorMesaages;
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.model.ContactRequest;
import com.sagaranand.website.model.ContactResponse;
import com.sagaranand.website.model.EmailSiteExistsResponse;
import com.sagaranand.website.model.EmailSiteExsistsRequest;
import com.sagaranand.website.model.ServiceResponse;
import com.sagaranand.website.model.SessionResponse;
import com.sagaranand.website.model.UserInfo;
import com.sagaranand.website.services.DaoService;
import com.sagaranand.website.utilities.MailUtilities;
import com.sagaranand.website.validations.SanitizerImpl;
import com.sagaranand.website.validations.ValidatorImpl;

/**
 * @author sanand5
 *
 */
@Controller
public class ApiController {

	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private GenericClient client;

	@Autowired
	private MailImpl mail;

	@Autowired
	private MailUtilities mailUtilities;

	@Autowired
	private ValidatorImpl validator;

	@Autowired
	private SanitizerImpl sanitizer;

	@Autowired
	private DaoService daoService;

	/**
	 * The /sendContactMailEndpoint
	 * 
	 * @return Response containing the send status for admin and user mails
	 */
	@RequestMapping(value = ApiEndpoints.API_GATEWAY + ApiEndpoints.ROOT
			+ ApiEndpoints.CONTACT_ENDPOINT, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ContactResponse> sendContactMail(@RequestBody ContactRequest contactRequest) {
		try {
			if (contactRequest.getPhone() == null) {
				contactRequest.setPhone("");
			}

			if (!validator.validateString(contactRequest.getName())
					|| !validator.validateString(contactRequest.getMessage())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ContactResponse(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())));
			} else if (!validator.validateEmail(contactRequest.getEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ContactResponse(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())));
			} else if (!validator.validateStringContent(contactRequest.getPhone())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ContactResponse(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())));
			}

			// prepare the message for the admin
			String adminMail = mailUtilities.prepareContactMessageForAdmin(
					sanitizer.sanitizeForBlocksAndFormatting(contactRequest.getName()),
					sanitizer.sanitizeForBlocksAndFormatting(contactRequest.getEmail()),
					sanitizer.sanitizeForBlocksAndFormatting(contactRequest.getPhone()),
					sanitizer.sanitizeForBlocksAndFormatting(contactRequest.getMessage()));

			// send the mail to the admin
			MandrillMessageStatus adminResp = mail.sendMail(mailUtilities.getContactAdminEmail(),
					mailUtilities.getContactAdminName(), mailUtilities.getContactAdminSubject(), adminMail);

			// send the acknowledge mail to the end user
			MandrillMessageStatus userResp = mail.sendMail(contactRequest.getEmail(), contactRequest.getName(),
					mailUtilities.getContactReplySubject(), mailUtilities.prepareContactReplyMessage(
							sanitizer.sanitizeForBlocksAndFormatting(contactRequest.getName())));

			if (adminResp.getStatus().equals("sent") && userResp.getStatus().equals("sent")) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ContactResponse(new ServiceResponse(HttpStatus.OK.value(), adminResp.getStatus()),
								new ServiceResponse(HttpStatus.OK.value(), userResp.getStatus())));
			} else if (adminResp.getStatus().equals("sent") || !userResp.getStatus().equals("sent")) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ContactResponse(new ServiceResponse(HttpStatus.OK.value(), adminResp.getStatus()),
								new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), userResp.getStatus())));
			} else if (!adminResp.getStatus().equals("sent") || userResp.getStatus().equals("sent")) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ContactResponse(
								new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), adminResp.getStatus()),
								new ServiceResponse(HttpStatus.OK.value(), userResp.getStatus())));
			} else {
				if (adminResp.getStatus().equals("rejected") || userResp.getStatus().equals("rejected")) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ContactResponse(
							new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), adminResp.getStatus()),
							new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), userResp.getStatus())));
				} else if (adminResp.getStatus().equals("invalid") || userResp.getStatus().equals("invalid")) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ContactResponse(
							new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), adminResp.getStatus()),
							new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), userResp.getStatus())));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ContactResponse(
						new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
								HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
						new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
								HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())));
	}

	@RequestMapping(value = "api/emailSiteExists", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<EmailSiteExistsResponse> isEmailSiteExists(
			@RequestBody EmailSiteExsistsRequest emailSiteExsistsRequest) {
		try {

			// validate the input of TenantEmail and SiteName here
			if (!validator.validateEmail(emailSiteExsistsRequest.getEmail())
					|| !validator.validateUsername(emailSiteExsistsRequest.getSiteName())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
						.body(new EmailSiteExistsResponse(true, true));
			}

			// now, call the Dal-layer functions
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new EmailSiteExistsResponse(
							this.daoService.isTenantExists(emailSiteExsistsRequest.getEmail()),
							this.daoService.isSiteExists(emailSiteExsistsRequest.getSiteName())));

		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new EmailSiteExistsResponse(true, true));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new EmailSiteExistsResponse(true, true));
		}
	}

}
