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
import com.sagaranand.website.orm.Tenant;

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

	/**
	 * 
	 * Checks if the tenantEmail exists in the database or not
	 * 
	 * @param email
	 * @return the boolean value stating if the Tenant email exists or not
	 * @throws DalException
	 */
	public boolean isTenantExists(String email) throws ServiceException;

	/**
	 * 
	 * Checks if the siteName already exists in the database
	 * 
	 * @param siteName
	 * @return the boolean value stating if the siteName already exists or not
	 * @throws DalException
	 */
	public boolean isSiteExists(String siteName) throws ServiceException;

	/**
	 * 
	 * To get the TenantDetails from the database based on the the ID passed
	 * 
	 * @param email
	 * @return the user credentials based on the ID passed
	 * @throws DalException
	 */
	public Tenant getTenant(String tenantId) throws ServiceException;

	/**
	 * 
	 * To get the TenantDetails from the database based on the Email passed
	 * 
	 * @param email
	 * @return the user credentials based on the email passed
	 * @throws DalException
	 */
	public Tenant getTenantByEmail(String email) throws ServiceException;

	/**
	 * 
	 * To get the TenantDetails from the database based on the SiteName passed
	 * 
	 * @param email
	 * @return the user credentials based on the SiteName passed
	 * @throws DalException
	 */
	public Tenant getTenantBySite(String site) throws ServiceException;

	/**
	 * 
	 * Returns the tenantID from the db based on the site passed
	 * 
	 * @param site
	 * @return the tenantId associated with the given site
	 * @throws DalException
	 */
	public String getTenantIdBySite(String site) throws ServiceException;

}
