/**
 * 
 */
package com.sagaranand.website.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.sagaranand.website.client.GenericClient;
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.model.ContactRequest;
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

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "status", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ServiceResponse> getStatus() {
		ServiceResponse statusResponse = client.getServiceStatus();
		return ResponseEntity.status(statusResponse.getStatus()).body(statusResponse);
	}

	@RequestMapping(value = "sendContactMail", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ServiceResponse> sendContactMail(@RequestBody ContactRequest contactRequest) {
		try {
			// prepare the message for the admin
			String adminMail = mailUtilities.prepareContactMessageForAdmin(contactRequest.getName(),
					contactRequest.getEmail(), contactRequest.getPhone(), contactRequest.getMessage());
			
			// send the mail to the admin
			MandrillMessageStatus adminResp = mail.SendMail("query@sagaranand.com", "Sagar Anand",
					"Query Received from SagarAnand.com Contact Form", adminMail);
			System.out.println("Admin mail status: " + adminResp.getStatus());
			
			// send the acknowledge mail to the end user
			MandrillMessageStatus userResp = mail.SendMail(contactRequest.getEmail(), contactRequest.getName(),
					mailUtilities.getContactReplySubject(), mailUtilities.getContactReplyMessage());
			System.out.println("User mail status: " + userResp.getStatus());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ResponseEntity.status(200).body(null);
	}

}
