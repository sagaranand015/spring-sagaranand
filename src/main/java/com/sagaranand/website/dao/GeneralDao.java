/**
 * 
 */
package com.sagaranand.website.dao;

import com.sagaranand.website.exceptions.DalException;

/**
 * @author sanand5
 *
 */
public interface GeneralDao {

	public boolean checkDbStatus() throws DalException;
	
}
