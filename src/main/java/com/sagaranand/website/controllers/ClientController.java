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

	@RequestMapping(value = ApiEndpoints.ROOT)
	public String index() {
		return "index";
	}

	@RequestMapping(value = ApiEndpoints.STATUSENDPOINT, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ServiceResponse> getStatus() {
		ServiceResponse statusResponse = client.getServiceStatus();
		return ResponseEntity.status(statusResponse.getStatus()).body(statusResponse);
	}

	@RequestMapping(value = ApiEndpoints.CONTACTENDPOINT, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ContactResponse> sendContactMail(@RequestBody ContactRequest contactRequest) {
		try {
			// prepare the message for the admin
			String adminMail = mailUtilities.prepareContactMessageForAdmin(contactRequest.getName(),
					contactRequest.getEmail(), contactRequest.getPhone(), contactRequest.getMessage());

			// send the mail to the admin
			MandrillMessageStatus adminResp = mail.SendMail(mailUtilities.getContactAdminEmail(),
					mailUtilities.getContactAdminName(), mailUtilities.getContactAdminSubject(), adminMail);
			System.out.println("Admin mail status: " + adminResp.getStatus());

			// send the acknowledge mail to the end user
			MandrillMessageStatus userResp = mail.SendMail(contactRequest.getEmail(), contactRequest.getName(),
					mailUtilities.getContactReplySubject(), mailUtilities.getContactReplyMessage());
			System.out.println("User mail status: " + userResp.getStatus());

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

}
