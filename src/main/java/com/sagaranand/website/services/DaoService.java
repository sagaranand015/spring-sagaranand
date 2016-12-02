/**
 * 
 */
package com.sagaranand.website.services;

import java.util.List;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.model.RegisterTenantRequest;
import com.sagaranand.website.model.RegisterTenantResponse;
import com.sagaranand.website.orm.Admin;

/**
 * @author sanand5
 *
 */
public interface DaoService {

	/**
	 * @return the db status as boolean
	 * @throws DalException
	 */
	public boolean checkdbStatus() throws ServiceException;

	/**
	 * @return all the admins in the db
	 * @throws ServiceException
	 * @throws DalException
	 */
	public List<Admin> getAllAdmins() throws ServiceException;

	/**
	 * @param username
	 * @return the admin record based on the username passed
	 * @throws ServiceException
	 * @throws DalException
	 */
	public Admin getAdminByUsername(String username) throws ServiceException;

	/**
	 * Register a new admin in the database
	 * 
	 * @param admin
	 * @throws DalException
	 */
	public boolean registerAdmin(Admin admin) throws ServiceException;

	/**
	 * 
	 * to get the admin record based on an email address
	 * 
	 * @param adminEmail
	 * @return the Admin record based on Email Address
	 * @throws DalException
	 */
	public Admin getAdminByEmail(String adminEmail) throws ServiceException;

	/**
	 * 
	 * register a new tenant and a new site for the passed tenant
	 * 
	 * @param tenant
	 * @return the boolean response addTenant and addSite operations
	 * @throws DalException
	 */
	public RegisterTenantResponse registerTenant(RegisterTenantRequest tenant) throws ServiceException;

}
