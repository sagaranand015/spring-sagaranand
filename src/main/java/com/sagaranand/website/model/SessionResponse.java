/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class SessionResponse extends ServiceResponse {

	private UserInfo userDetails;

	public SessionResponse(int status, String message, UserInfo userDetails) {
		super(status, message);
		this.userDetails = userDetails;
	}

	public SessionResponse() {
		// TODO Auto-generated constructor stub
	}

	public UserInfo getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserInfo userDetails) {
		this.userDetails = userDetails;
	}

}
