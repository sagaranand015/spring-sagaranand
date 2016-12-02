/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class RegisterTenantRequest {

	private String tenantEmail;

	private String tenantName;

	private String tenantPwd;

	private String siteName;

	public RegisterTenantRequest() {
		super();
	}

	public RegisterTenantRequest(String tenantEmail, String tenantName, String tenantPwd, String siteName) {
		super();
		this.tenantEmail = tenantEmail;
		this.tenantName = tenantName;
		this.tenantPwd = tenantPwd;
		this.siteName = siteName;
	}

	public String getTenantEmail() {
		return tenantEmail;
	}

	public void setTenantEmail(String tenantEmail) {
		this.tenantEmail = tenantEmail;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantPwd() {
		return tenantPwd;
	}

	public void setTenantPwd(String tenantPwd) {
		this.tenantPwd = tenantPwd;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
