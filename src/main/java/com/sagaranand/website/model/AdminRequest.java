/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class AdminRequest {

	private String adminName;

	private String adminUsername;

	private String adminEmail;

	private String adminContact;

	private String adminPwd;

	private String salt;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public AdminRequest() {
		super();
	}

	public AdminRequest(String adminName, String adminUsername, String adminEmail, String adminContact, String adminPwd,
			String salt) {
		super();
		this.adminName = adminName;
		this.adminUsername = adminUsername;
		this.adminEmail = adminEmail;
		this.adminContact = adminContact;
		this.adminPwd = adminPwd;
		this.salt = salt;
	}

}
