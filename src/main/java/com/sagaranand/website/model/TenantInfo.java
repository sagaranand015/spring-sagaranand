/**
 * 
 */
package com.sagaranand.website.model;

import java.security.Principal;

/**
 * @author sanand5
 *
 */
public class TenantInfo implements Principal {

	private String tenantId;

	private String tenantName;

	private String tenantEmail;

	private String tenantContact;
	
	private int tenantType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.Principal#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public TenantInfo() {
		super();
	}

	public TenantInfo(String tenantId, String tenantName, String tenantEmail, String tenantContact, int tenantType) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.tenantEmail = tenantEmail;
		this.tenantContact = tenantContact;
		this.tenantType = tenantType;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantEmail() {
		return tenantEmail;
	}

	public void setTenantEmail(String tenantEmail) {
		this.tenantEmail = tenantEmail;
	}

	public String getTenantContact() {
		return tenantContact;
	}

	public void setTenantContact(String tenantContact) {
		this.tenantContact = tenantContact;
	}

	public int getTenantType() {
		return tenantType;
	}

	public void setTenantType(int tenantType) {
		this.tenantType = tenantType;
	}
	
}
