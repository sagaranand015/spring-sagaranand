/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class SessionResponse extends ServiceResponse {

	private User userDetails;

	public SessionResponse(int status, String message, User userDetails) {
		super(status, message);
		this.userDetails = userDetails;
	}

	public SessionResponse() {
		// TODO Auto-generated constructor stub
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

}
