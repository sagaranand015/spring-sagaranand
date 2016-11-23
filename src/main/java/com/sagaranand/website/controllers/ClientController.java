/**
 * 
 */
package com.sagaranand.website.controllers;

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
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.model.ContactRequest;
import com.sagaranand.website.model.ContactResponse;
import com.sagaranand.website.model.ServiceResponse;
import com.sagaranand.website.utilities.MailUtilities;
import com.sagaranand.website.validations.SanitizerImpl;
import com.sagaranand.website.validations.ValidatorImpl;

/**
 * @author sanand5
 *
 */
@Controller
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

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

	/**
	 * The / Endpoint
	 * 
	 * @return home.jsp - the root page
	 */
	@RequestMapping(value = ApiEndpoints.ROOT)
	public String index() {
		return ApiEndpoints.ROOTPAGE;
	}

	/**
	 * The /home Endpoint
	 * 
	 * @return home.jsp - the root page
	 */
	@RequestMapping(value = ApiEndpoints.ROOTPAGE)
	public String home() {
		return ApiEndpoints.ROOTPAGE;
	}

	/**
	 * The /status Endpoint
	 * 
	 * @return Response code for service status
	 */
	@RequestMapping(value = ApiEndpoints.STATUSENDPOINT, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ServiceResponse> getStatus() {
		ServiceResponse statusResponse = client.getServiceStatus();
		return ResponseEntity.status(statusResponse.getStatus()).body(statusResponse);
	}

	/**
	 * The /sendContactMailEndpoint
	 * 
	 * @return Response containing the send status for admin and user mails
	 */
	@RequestMapping(value = ApiEndpoints.CONTACTENDPOINT, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ContactResponse> sendContactMail(@RequestBody ContactRequest contactRequest) {
		try {

			// validation of the input parameters
			if (!validator.validateString(contactRequest.getEmail())
					|| !validator.validateString(contactRequest.getName())
					|| !validator.validateString(contactRequest.getMessage())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ContactResponse(new ServiceResponse(HttpStatus.BAD_REQUEST.value(), ""),
								new ServiceResponse(HttpStatus.BAD_REQUEST.value(), "")));
			} else if (!validator.validateStringIsNull(contactRequest.getPhone())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ContactResponse(new ServiceResponse(HttpStatus.BAD_REQUEST.value(), ""),
								new ServiceResponse(HttpStatus.BAD_REQUEST.value(), "")));
			} else if (!validator.validateEmail(contactRequest.getEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ContactResponse(new ServiceResponse(HttpStatus.BAD_REQUEST.value(), ""),
								new ServiceResponse(HttpStatus.BAD_REQUEST.value(), "")));
			} else if (!validator.validateStringContent(contactRequest.getName())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ContactResponse(new ServiceResponse(HttpStatus.BAD_REQUEST.value(), ""),
								new ServiceResponse(HttpStatus.BAD_REQUEST.value(), "")));
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
			MandrillMessageStatus userResp = mail.sendMail(
					sanitizer.sanitizeForBlocksAndFormatting(contactRequest.getEmail()),
					sanitizer.sanitizeForBlocksAndFormatting(contactRequest.getName()),
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
				.body(new ContactResponse(new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ""),
						new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "")));
	}

	/**
	 * the /login endpoint
	 * 
	 * @return The login page - login.jsp
	 */
	@RequestMapping(value = ApiEndpoints.LOGINENDPOINT, method = RequestMethod.GET)
	public String login() {
		return ApiEndpoints.LOGINENDPOINT;
	}

}
