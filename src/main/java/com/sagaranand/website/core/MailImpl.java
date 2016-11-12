/**
 * 
 */
package com.sagaranand.website.core;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.sagaranand.website.utilities.MailUtilities;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;

/**
 * @author sanand5
 *
 */
public class MailImpl implements Mail {

	private static final Logger logger = LoggerFactory.getLogger(MailImpl.class);

	@Autowired
	private MailUtilities mailUtilities;

	private String apiKey;

	private String fromEmail;

	private String fromName;

	MandrillApi mandrillApi;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.core.Mail#SendMail(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public MandrillMessageStatus sendMail(String toEmail, String toName, String subject, String message)
			throws MandrillApiError, IOException, Exception {
		try {
			if (mandrillApi == null) {
				mandrillApi = mailUtilities.prepareMandrillApi(this.getApiKey());
			}

			ArrayList<Recipient> recipients = new ArrayList<Recipient>();
			Recipient rec = new Recipient();
			rec.setEmail(toEmail);
			rec.setName(toName);
			recipients.add(rec);

			MandrillMessage mailMessage = mailUtilities.prepareMandrillMessage(subject, message, this.getFromEmail(),
					this.getFromName(), recipients);
			if(mailMessage != null) {
				MandrillMessageStatus[] sendStatus = mandrillApi.messages().send(mailMessage, true);
				return sendStatus[0];
			}
			return null;
			
		} catch (MandrillApiError e) {
			logger.error(e.getMandrillErrorMessage());
			logger.error(e.getMandrillErrorName());
			logger.error(e.getMandrillErrorStatus(), e);
			throw e;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.core.Mail#SendMail(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public MandrillMessageStatus sendMail(String toEmail, String toName, String fromEmail, String fromName,
			String subject, String message) throws MandrillApiError, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.core.Mail#SendMail(java.lang.String[],
	 * java.lang.String[], java.lang.String, java.lang.String)
	 */
	public MandrillMessageStatus[] sendMail(String[] toEmail, String[] toName, String subject, String message)
			throws MandrillApiError, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.core.Mail#SendMail(java.lang.String[],
	 * java.lang.String[], java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public MandrillMessageStatus[] sendMail(String[] toEmail, String[] toName, String fromEmail, String fromName,
			String subject, String message) throws MandrillApiError, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
