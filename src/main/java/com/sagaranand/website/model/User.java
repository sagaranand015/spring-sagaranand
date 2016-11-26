/**
 * 
 */
package com.sagaranand.website.model;

/**
 * @author sanand5
 *
 */
public class User {

	private int userId;

	private String userName;

	private String name;

	private String userEmail;

	public User() {
		super();
	}

	public User(int userId, String userName, String name, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.userEmail = userEmail;
	}

	public User(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
