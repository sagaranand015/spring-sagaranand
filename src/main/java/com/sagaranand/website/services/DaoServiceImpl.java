/**
 * 
 */
package com.sagaranand.website.services;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sagaranand.website.dao.Dao;
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
	public List<Admin> getAllAdmins() throws ServiceException {
		try {
			return this.dao.getAllAdmins();
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.AdminService#getAdminByUsername(java.lang
	 * .String)
	 */
	public Admin getAdminByUsername(String username) throws ServiceException {
		try {
			return this.dao.getAdminByUsername(username);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.services.GeneralService#checkdbStatus()
	 */
	public boolean checkdbStatus() throws ServiceException {
		try {
			return this.dao.checkDbStatus();
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#registerAdmin(com.sagaranand.
	 * website.model.Admin)
	 */
	public boolean registerAdmin(Admin admin) throws ServiceException {
		try {
			return this.dao.registerAdmin(admin);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#getAdminByEmail(java.lang.
	 * String)
	 */
	public Admin getAdminByEmail(String adminEmail) throws ServiceException {
		try {
			return this.dao.getAdminByEmail(adminEmail);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#registerTenant(com.sagaranand.
	 * website.model.RegisterTenantRequest)
	 */
	public RegisterTenantResponse registerTenant(RegisterTenantRequest tenant) throws ServiceException {
		try {
			return this.dao.registerTenant(tenant);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.services.DaoService#isTenantExists(java.lang.
	 * String)
	 */
	public boolean isTenantExists(String email) throws ServiceException {
		try {
			return this.dao.isTenantExists(email);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#isSiteExists(java.lang.String)
	 */
	public boolean isSiteExists(String siteName) throws ServiceException {
		try {
			return this.dao.isSiteExists(siteName);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#getTenant(java.lang.String)
	 */
	public Tenant getTenant(String tenantId) throws ServiceException {
		try {
			return this.dao.getTenant(tenantId);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#getTenantByEmail(java.lang.
	 * String)
	 */
	public Tenant getTenantByEmail(String email) throws ServiceException {
		try {
			return this.dao.getTenantByEmail(email);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#getTenantBySite(java.lang.
	 * String)
	 */
	public Tenant getTenantBySite(String site) throws ServiceException {
		try {
			return this.dao.getTenantBySite(site);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.services.DaoService#getTenantIdBySite(java.lang.
	 * String)
	 */
	public String getTenantIdBySite(String site) throws ServiceException {
		try {
			return this.dao.getTenantIdBySite(site);
		} catch (DalException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
