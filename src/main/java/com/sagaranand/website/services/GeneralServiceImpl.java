/**
 * 
 */
package com.sagaranand.website.services;

import org.apache.log4j.Logger;
import org.jvnet.hk2.annotations.Service;

import com.sagaranand.website.dao.AdminDao;
import com.sagaranand.website.dao.GeneralDao;
import com.sagaranand.website.exceptions.DalException;

/**
 * @author sanand5
 *
 */
@Service
public class GeneralServiceImpl implements GeneralService {

	private static final Logger logger = Logger.getLogger(GeneralServiceImpl.class);

	private GeneralDao generalDao;

	public void setGeneralDao(GeneralDao generalDao) {
		this.generalDao = generalDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.services.GeneralService#checkdbStatus()
	 */
	public boolean checkdbStatus() throws DalException {
		try {
			return this.generalDao.checkDbStatus();
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}
