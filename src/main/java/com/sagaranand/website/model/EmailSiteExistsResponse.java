/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class EmailSiteExistsResponse {

	private boolean isEmailExists;

	private boolean isSiteExists;

	public EmailSiteExistsResponse() {
		super();
	}

	public EmailSiteExistsResponse(boolean isEmailExists, boolean isSiteExists) {
		super();
		this.isEmailExists = isEmailExists;
		this.isSiteExists = isSiteExists;
	}

	public boolean isEmailExists() {
		return isEmailExists;
	}

	public void setEmailExists(boolean isEmailExists) {
		this.isEmailExists = isEmailExists;
	}

	public boolean isSiteExists() {
		return isSiteExists;
	}

	public void setSiteExists(boolean isSiteExists) {
		this.isSiteExists = isSiteExists;
	}

}
