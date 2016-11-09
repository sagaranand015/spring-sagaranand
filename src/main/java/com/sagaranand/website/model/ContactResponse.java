/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class ContactResponse {

	private ServiceResponse adminResp;
	
	private ServiceResponse userResp;

	public ServiceResponse getAdminResp() {
		return adminResp;
	}

	public void setAdminResp(ServiceResponse adminResp) {
		this.adminResp = adminResp;
	}

	public ServiceResponse getUserResp() {
		return userResp;
	}

	public void setUserResp(ServiceResponse userResp) {
		this.userResp = userResp;
	}

	public ContactResponse(ServiceResponse adminResp, ServiceResponse userResp) {
		super();
		this.adminResp = adminResp;
		this.userResp = userResp;
	}
	
}
