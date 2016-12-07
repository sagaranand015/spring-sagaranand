/**
 * 
 */
package com.sagaranand.website.model;

import java.security.Principal;

/**
 * @author sanand5
 *
 */
public class UserInfo implements Principal {

	private String id;

	private String name;

	private String email;

	private String contact;
	
	private int type;

	public UserInfo() {
		super();
	}

	public UserInfo(String id, String name, String email, String contact, int type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
