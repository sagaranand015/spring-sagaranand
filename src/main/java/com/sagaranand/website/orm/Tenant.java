/**
 * 
 */
package com.sagaranand.website.orm;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sanand5
 *
 */
@Entity
@Table(name = "Tenant")
public class Tenant {

	@Id
	@Column(name = "tenantId")
	private String tenantId;

	@Column(name = "tenantName")
	private String tenantName;

	@Column(name = "tenantUsername", nullable = false, unique = true)
	private String tenantUsername;

	@Column(name = "tenantEmail")
	private String tenantEmail;

	@Column(name = "tenantContact")
	private String tenantContact;

	@Column(name = "tenantPwd")
	private String tenantPwd;

	@Column(name = "salt")
	private String salt;

	@Column(name = "tenantType")
	private int tenantType;

	@Column(name = "lastUpdatedOn")
	private Timestamp lastUpdatedOn;

	public Tenant() {
		super();
	}

	public Tenant(String tenantId, String tenantName, String tenantUsername, String tenantEmail, String tenantContact,
			String tenantPwd, String salt, int tenantType, Timestamp lastUpdatedOn) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.tenantUsername = tenantUsername;
		this.tenantEmail = tenantEmail;
		this.tenantContact = tenantContact;
		this.tenantPwd = tenantPwd;
		this.salt = salt;
		this.tenantType = tenantType;
		this.lastUpdatedOn = lastUpdatedOn;
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

	public String getTenantUsername() {
		return tenantUsername;
	}

	public void setTenantUsername(String tenantUsername) {
		this.tenantUsername = tenantUsername;
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

	public String getTenantPwd() {
		return tenantPwd;
	}

	public void setTenantPwd(String tenantPwd) {
		this.tenantPwd = tenantPwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getTenantType() {
		return tenantType;
	}

	public void setTenantType(int tenantType) {
		this.tenantType = tenantType;
	}

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

}
