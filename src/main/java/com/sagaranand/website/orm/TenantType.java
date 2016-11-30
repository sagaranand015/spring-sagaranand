/**
 * 
 */
package com.sagaranand.website.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sanand5
 *
 */
@Entity
@Table(name = "TenantType")
public class TenantType {

	@Id
	@Column(name = "tenantTypeId")
	private int tenantTypeId;

	@Column(name = "tenantType")
	private String tenantType;
	
	public TenantType() {
		super();
	}

	public TenantType(int tenantTypeId, String tenantType) {
		super();
		this.tenantTypeId = tenantTypeId;
		this.tenantType = tenantType;
	}

	public int getTenantTypeId() {
		return tenantTypeId;
	}

	public void setTenantTypeId(int tenantTypeId) {
		this.tenantTypeId = tenantTypeId;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

}
