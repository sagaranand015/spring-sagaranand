/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class EmailSiteExsistsRequest {

	private String email;

	private String siteName;

	public EmailSiteExsistsRequest() {
		super();
	}

	public EmailSiteExsistsRequest(String email, String siteName) {
		super();
		this.email = email;
		this.siteName = siteName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
