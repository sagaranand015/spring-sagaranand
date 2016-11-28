/**
 * 
 */
package com.sagaranand.website.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.sagaranand.website.client.GenericClient;
import com.sagaranand.website.constants.ApiEndpoints;
import com.sagaranand.website.constants.ErrorCodes;
import com.sagaranand.website.constants.ErrorMesaages;
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.orm.Admin;
import com.sagaranand.website.model.ContactRequest;
import com.sagaranand.website.model.ContactResponse;
import com.sagaranand.website.model.ServiceResponse;
import com.sagaranand.website.model.SessionResponse;
import com.sagaranand.website.model.User;
import com.sagaranand.website.services.DaoService;
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

	@Autowired
	private DaoService daoService;

	/**
	 * The / Endpoint
	 * 
	 * @return home.jsp - the root page
	 */
	@RequestMapping(value = ApiEndpoints.ROOT)
	public String index(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request.getRequestURL().toString());
		System.out.println(request.getLocalName());

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
	public @ResponseBody ResponseEntity<ServiceResponse> getStatus(HttpServletRequest request) {
		try {
			if (!daoService.checkdbStatus()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.body(new ServiceResponse(ErrorCodes.DB_FAILED, ErrorMesaages.DB_FAILED));
			}
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new ServiceResponse(ErrorCodes.OK, ErrorMesaages.OK));
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.DB_OPERATION_FAILED, ErrorMesaages.DB_OPERATION_FAILED));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR));
		}
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
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ContactResponse(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())));
			} else if (!validator.validateStringIsNull(contactRequest.getPhone())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ContactResponse(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())));
			} else if (!validator.validateEmail(contactRequest.getEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ContactResponse(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())));
			} else if (!validator.validateStringContent(contactRequest.getName())) {
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

	/**
	 * the /login endpoint
	 * 
	 * @return The login page - login.jsp
	 */
	@RequestMapping(value = ApiEndpoints.LOGINENDPOINT)
	public String login(@RequestParam(value = "valid", required = false) String valid,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "session", required = false) String session, Model model) {
		if (valid != null && valid.equals("false")) {
			model.addAttribute("valid", false);
		}
		if (logout != null && logout.equals("true")) {
			model.addAttribute("logout", true);
		}
		if (session != null && session.equals("false")) {
			model.addAttribute("session", false);
		}
		return ApiEndpoints.LOGINENDPOINT;
	}

	/**
	 * the /admin endpoint for admin access portal
	 * 
	 * @return the admin page
	 */
	@RequestMapping(value = "admin")
	public String adminPage() {
		return "admin";
	}

	/**
	 * 
	 * @param request
	 * @return the SessionResponse containing session information
	 */
	@RequestMapping(value = "session", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<SessionResponse> getSession(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session != null) {
				User loggedInUser = (User) session.getAttribute("user");
				if (loggedInUser != null) {
					return ResponseEntity.status(HttpStatus.OK.value())
							.body(new SessionResponse(ErrorCodes.OK, ErrorMesaages.OK, loggedInUser));
				}
			}
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new SessionResponse(ErrorCodes.UNAUTHORIZED, ErrorMesaages.UNAUTHORIZED, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
					new SessionResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR, null));
		}
	}

	/**
	 * 
	 * @param admin
	 * @return the response containing the success/fail response for Admin
	 *         Record insertion
	 */
	@RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ServiceResponse> registerAdmin(@RequestBody Admin admin) {
		try {
			if (!validator.validateString(admin.getAdminName()) || !validator.validateString(admin.getAdminContact())
					|| !validator.validateString(admin.getAdminPwd()) || !validator.validateString(admin.getSalt())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			} else if (!validator.validateEmail(admin.getAdminEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			} else if (!validator.validateUsername(admin.getAdminUsername())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			} else if (!validator.validatePassword(admin.getAdminPwd())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			}

			if (!this.daoService.registerAdmin(admin)) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
						new ServiceResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR));
			}

			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new ServiceResponse(ErrorCodes.OK, ErrorMesaages.OK));
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.DB_OPERATION_FAILED, ErrorMesaages.DB_OPERATION_FAILED));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR));
		}
	}

}
