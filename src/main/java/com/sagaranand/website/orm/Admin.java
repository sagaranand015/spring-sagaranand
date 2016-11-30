/**
 * 
 */
package com.sagaranand.website.orm;

import java.sql.Timestamp;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author sanand5
 *
 */
@Entity
@Table(name = "Admin")
public class Admin {

	@Id
	private String adminId;

	@Column(name = "adminName")
	private String adminName;

	@Column(name = "adminUsername", nullable = false, unique = true)
	private String adminUsername;

	@Column(name = "adminEmail", nullable = false, unique = true)
	private String adminEmail;

	@Column(name = "adminContact")
	private String adminContact;

	@Column(name = "adminPwd")
	private String adminPwd;

	@Column(name = "lastUpdatedOn")
	private Timestamp lastUpdatedOn;

	public Admin() {
		super();
	}

	public Admin(String adminId, String adminName, String adminUsername, String adminEmail, String adminContact,
			String adminPwd, Timestamp lastUpdatedOn) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminUsername = adminUsername;
		this.adminEmail = adminEmail;
		this.adminContact = adminContact;
		this.adminPwd = adminPwd;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

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

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Override
	public String toString() {
		return "id:" + this.getAdminId() + ", " + "Name:" + this.getAdminName() + ", " + "Username:"
				+ this.getAdminUsername() + ", " + "Email:" + this.getAdminEmail();
	}

	@PrePersist
	public void setAdminIdAsString() {
		this.setAdminId(UUID.randomUUID().toString());
	}

}
