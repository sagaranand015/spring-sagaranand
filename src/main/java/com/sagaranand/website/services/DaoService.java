/**
 * 
 */
package com.sagaranand.website.services;

import java.util.List;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.exceptions.ServiceException;
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
	public boolean checkdbStatus() throws DalException;
	
	/**
	 * @return all the admins in the db
	 * @throws ServiceException
	 * @throws DalException
	 */
	public List<Admin> getAllAdmins() throws ServiceException, DalException;

	/**
	 * @param username
	 * @return the admin record based on the username passed
	 * @throws ServiceException
	 * @throws DalException
	 */
	public Admin getAdminByUsername(String username) throws ServiceException, DalException;

	/**
	 * Register a new admin in the database
	 * @param admin
	 * @throws DalException
	 */
	public boolean registerAdmin(Admin admin) throws DalException;
	
}
