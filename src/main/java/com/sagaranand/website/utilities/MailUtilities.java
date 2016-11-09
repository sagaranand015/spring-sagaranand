/**
 * 
 */
package com.sagaranand.website.utilities;

import java.util.ArrayList;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;

/**
 * @author sanand5
 *
 */
public class MailUtilities {

	private String contactReplySubject;

	private String contactReplyMessage;
	
	private String contactAdminEmail;
	
	private String contactAdminName;
	
	private String contactAdminSubject;
	
	public String getContactAdminEmail() {
		return contactAdminEmail;
	}

	public void setContactAdminEmail(String contactAdminEmail) {
		this.contactAdminEmail = contactAdminEmail;
	}

	public String getContactAdminName() {
		return contactAdminName;
	}

	public void setContactAdminName(String contactAdminName) {
		this.contactAdminName = contactAdminName;
	}

	public String getContactAdminSubject() {
		return contactAdminSubject;
	}

	public void setContactAdminSubject(String contactAdminSubject) {
		this.contactAdminSubject = contactAdminSubject;
	}

	public String getContactReplySubject() {
		return contactReplySubject;
	}

	public void setContactReplySubject(String contactReplySubject) {
		this.contactReplySubject = contactReplySubject;
	}

	public String getContactReplyMessage() {
		return contactReplyMessage;
	}

	public void setContactReplyMessage(String contactReplyMessage) {
		this.contactReplyMessage = contactReplyMessage;
	}

	/**
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @param message
	 * @return the html message for the Admin, containing all the client details
	 */
	public String prepareContactMessageForAdmin(String name, String email, String phone, String message) {
		StringBuffer mailMessage = new StringBuffer(
				"<h1>Contact Query Received</h1> hi Admin, Please find the details of the query below(<b>received from query@sagaranand.com</b>): <br /><br />");
		mailMessage.append("Name: <b>" + name + "</b><br/>");
		mailMessage.append("Email: <b>" + email + "</b><br/>");
		mailMessage.append("Phone: <b>" + phone + "</b><br/>");
		mailMessage.append("Message: <b>" + message + "</b><br/>");
		mailMessage.append(
				"<br /> ------------------------------------------------------------------ <br />Please do not reply to this automated mail");
		return mailMessage.toString();
	}

	/**
	 * @param subject
	 * @param message
	 * @param fromEmail
	 * @param fromName
	 * @param recipient
	 * @return the MandrillMailMessage prepared with all the parameters
	 * @throws Exception
	 */
	public MandrillMessage prepareMandrillMessage(String subject, String message, String fromEmail, String fromName,
			ArrayList<Recipient> recipients) throws Exception {
		try {
			MandrillMessage mailMessage = new MandrillMessage();
			mailMessage.setSubject(subject);
			mailMessage.setHtml(message);
			mailMessage.setAutoText(true);
			mailMessage.setFromEmail(fromEmail);
			mailMessage.setFromName(fromName);
			mailMessage.setTo(recipients);
			return mailMessage;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param apiKey
	 * @return the MandrillAPI object for calling the send function
	 */
	public MandrillApi prepareMandrillApi(String apiKey) {
		return new MandrillApi(apiKey);
	}

}
