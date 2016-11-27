/**
 * 
 */
package com.sagaranand.website.dao;

import java.util.List;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.model.Admin;
import com.sagaranand.website.model.AdminRequest;

/**
 * @author sanand5
 *
 */
public interface Dao {
	
	/**
	 * @return the db status as boolean. True:up, false:down
	 * @throws DalException
	 */
	public boolean checkDbStatus() throws DalException;
	
	/**
	 * @return all the admins in the db
	 * @throws DalException
	 */
	public List<Admin> getAllAdmins() throws DalException;

	/**
	 * @param username
	 * @return the Admin Record based on a username
	 * @throws DalException
	 */
	public Admin getAdminByUsername(String username) throws DalException;
	
	/**
	 * 
	 * @param admin
	 * @return the boolean value stating if the user has been added or not
	 * @throws DalException
	 */
	public boolean registerAdmin(Admin admin) throws DalException;

}
