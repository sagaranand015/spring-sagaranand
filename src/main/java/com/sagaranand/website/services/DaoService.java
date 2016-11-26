/**
 * 
 */
package com.sagaranand.website.services;

import java.util.List;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
public interface DaoService {

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
	 * @return the db status as boolean
	 * @throws DalException
	 */
	public boolean checkdbStatus() throws DalException;
}
