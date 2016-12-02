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
@Table(name = "Site")
public class Site {

	@Id
	@Column(name = "siteId")
	private String siteId;

	@Column(name = "siteName", nullable = false, unique = true)
	private String siteName;

	@Column(name = "siteTheme", nullable = false)
	private String siteTheme;

	@Column(name = "tenantId", nullable = false)
	private String tenantId;

	@Column(name = "lastUpdatedOn")
	private Timestamp lastUpdatedOn;

	public Site() {
		super();
	}

	public Site(String siteId, String siteName, String siteTheme, Timestamp lastUpdatedOn) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
		this.siteTheme = siteTheme;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteTheme() {
		return siteTheme;
	}

	public void setSiteTheme(String siteTheme) {
		this.siteTheme = siteTheme;
	}

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

}
