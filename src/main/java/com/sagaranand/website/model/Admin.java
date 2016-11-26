/**
 * 
 */
package com.sagaranand.website.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * @author sanand5
 *
 */
@Entity
@Table(name = "Admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Type(type = "int")
	@Column(name = "adminId", nullable = false, unique = true)
	private int adminId;

	@Column(name = "adminName")
	private String adminName;

	@Column(name = "adminUsername", nullable = false, unique = true)
	private String adminUserName;

	@Column(name = "adminEmail", nullable = false, unique = true)
	private String adminEmail;

	@Column(name = "adminContact")
	private String adminContact;

	@Column(name = "adminPwd")
	private String adminPwd;

	@Column(name = "salt")
	private String salt;

	@Column(name = "lastUpdatedOn")
	private Timestamp lastUpdatedOn;

	public Admin() {
		super();
	}
	
	public Admin(int adminId, String adminName, String adminUserName, String adminEmail, String adminContact,
			String adminPwd, String salt, Timestamp lastUpdatedOn) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminUserName = adminUserName;
		this.adminEmail = adminEmail;
		this.adminContact = adminContact;
		this.adminPwd = adminPwd;
		this.salt = salt;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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
				+ this.getAdminUserName() + ", " + "Email:" + this.getAdminEmail();
	}

}
