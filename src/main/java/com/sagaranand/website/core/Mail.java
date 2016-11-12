/**
 * 
 */
package com.sagaranand.website.core;

import java.io.IOException;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

/**
 * @author sanand5
 *
 */
public interface Mail {

	/**
	 * @param toEmail
	 * @param toName
	 * @param subject
	 * @param message
	 * @return MandrillMessageStatus containing the status of mails sent
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus sendMail(String toEmail, String toName, String subject, String message)
			throws MandrillApiError, IOException, Exception;

	/**
	 * @param toEmail
	 * @param toName
	 * @param fromEmail
	 * @param fromName
	 * @param subject
	 * @param message
	 * @return MandrillMessageStatus containing the status of mails sent
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus sendMail(String toEmail, String toName, String fromEmail, String fromName,
			String subject, String message) throws MandrillApiError, IOException, Exception;

	/**
	 * @param toEmail
	 * @param toName
	 * @param subject
	 * @param message
	 * @return MandrillMessageStatus containing the status of mails sent
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus[] sendMail(String toEmail[], String toName[], String subject, String message)
			throws MandrillApiError, IOException, Exception;

	/**
	 * @param toEmail
	 * @param toName
	 * @param fromEmail
	 * @param fromName
	 * @param subject
	 * @param message
	 * @return MandrillMessageStatus containing the status of mails sent
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public MandrillMessageStatus[] sendMail(String toEmail[], String toName[], String fromEmail, String fromName,
			String subject, String message) throws MandrillApiError, IOException, Exception;

}
