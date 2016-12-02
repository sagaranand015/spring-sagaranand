/**
 * 
 */
package com.sagaranand.website.dao;

import java.util.List;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.model.RegisterTenantRequest;
import com.sagaranand.website.model.RegisterTenantResponse;
import com.sagaranand.website.orm.Admin;

/**
 * @author sanand5
 *
 */
public interface Dao {

	/**
	 * To check the Db status using the query Show tables
	 * 
	 * @return the db status as boolean. True:up, false:down
	 * @throws DalException
	 */
	public boolean checkDbStatus() throws DalException;

	/**
	 * 
	 * To get all the admins present in the database
	 * 
	 * @return all the admins in the db
	 * @throws DalException
	 */
	public List<Admin> getAllAdmins() throws DalException;

	/**
	 * to get the admin record based on the username passed
	 * 
	 * @param username
	 * @return the Admin Record based on a username
	 * @throws DalException
	 */
	public Admin getAdminByUsername(String adminUsername) throws DalException;

	/**
	 * 
	 * to register a new admin into the db
	 * 
	 * @param admin
	 * @return the boolean value stating if the Admin has been added or not
	 * @throws DalException
	 */
	public boolean registerAdmin(Admin admin) throws DalException;

	/**
	 * 
	 * to get the admin record based on an email address
	 * 
	 * @param adminEmail
	 * @return the Admin record based on Email Address
	 * @throws DalException
	 */
	public Admin getAdminByEmail(String adminEmail) throws DalException;

	/**
	 * 
	 * register a new tenant and a new site for the passed tenant
	 * 
	 * @param tenant
	 * @return the boolean response addTenant and addSite operations
	 * @throws DalException
	 */
	public RegisterTenantResponse registerTenant(RegisterTenantRequest tenant) throws DalException;

}
