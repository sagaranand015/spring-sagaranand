/**
 * 
 */
package com.sagaranand.website.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sagaranand.website.dao.Dao;
import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
@Service
public class DaoServiceImpl implements DaoService {

	private static final Logger logger = Logger.getLogger(DaoServiceImpl.class);

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.services.AdminService#getAllAdmins()
	 */
	@Transactional
	public List<Admin> getAllAdmins() throws ServiceException, DalException {
		try {
			return this.dao.getAllAdmins();
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.AdminService#getAdminByUsername(java.lang
	 * .String)
	 */
	@Transactional
	public Admin getAdminByUsername(String username) throws ServiceException, DalException {
		try {
			return this.dao.getAdminByUsername(username);
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.services.GeneralService#checkdbStatus()
	 */
	public boolean checkdbStatus() throws DalException {
		try {
			return this.dao.checkDbStatus();
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}
