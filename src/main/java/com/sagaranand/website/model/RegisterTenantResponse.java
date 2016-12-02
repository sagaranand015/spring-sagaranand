/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class RegisterTenantResponse extends ServiceResponse {

	private boolean tenantResp;

	private boolean siteResp;

	public RegisterTenantResponse() {
		super();
	}

	public RegisterTenantResponse(int status, String message, boolean tenantResp, boolean siteResp) {
		super(status, message);
		this.tenantResp = tenantResp;
		this.siteResp = siteResp;
	}

	public RegisterTenantResponse(boolean tenantResp, boolean siteResp) {
		super();
		this.tenantResp = tenantResp;
		this.siteResp = siteResp;
	}

	public boolean isTenantResp() {
		return tenantResp;
	}

	public void setTenantResp(boolean tenantResp) {
		this.tenantResp = tenantResp;
	}

	public boolean isSiteResp() {
		return siteResp;
	}

	public void setSiteResp(boolean siteResp) {
		this.siteResp = siteResp;
	}

}
