/**
 * 
 */
package com.sagaranand.website.dao;

import java.util.List;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
public interface AdminDao {

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
	
	

}
