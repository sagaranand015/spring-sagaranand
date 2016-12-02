/**
 * 
 */
package com.sagaranand.website.dao;

import java.util.List;

import java.util.UUID;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.model.RegisterTenantRequest;
import com.sagaranand.website.model.RegisterTenantResponse;
import com.sagaranand.website.orm.Admin;

/**
 * @author sanand5
 *
 */
@Repository
public class DaoImpl implements Dao {

	private static final Logger logger = Logger.getLogger(DaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.dao.GeneralDao#checkDbStatus()
	 */
	@Transactional
	public boolean checkDbStatus() throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createNativeQuery("show tables");
			int res = query.getMaxResults();
			if (res >= 0) {
				return true;
			}
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.dao.Dao#getAllAdmins()
	 */
	@Transactional
	public List<Admin> getAllAdmins() throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Admin> adminList = session.createQuery("from Admin").getResultList();
			return adminList;
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.dao.AdminDao#getAdminByUsername(java.lang.String)
	 */
	@Transactional
	public Admin getAdminByUsername(String username) throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Admin where adminUsername=:adminUsername");
			query.setParameter("adminUsername", username);
			return (Admin) query.getSingleResult();
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.dao.Dao#registerAdmin(com.sagaranand.website.model
	 * .Admin)
	 */
	@Transactional
	public boolean registerAdmin(Admin admin) throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createNativeQuery(
					"insert into Admin(adminId, adminName, adminUsername, adminEmail,adminContact, adminPwd) values(:adminId,:adminName,:adminUsername,:adminEmail,:adminContact,:adminPwd)");
			query.setParameter("adminId", UUID.randomUUID().toString());
			query.setParameter("adminName", admin.getAdminName());
			query.setParameter("adminUsername", admin.getAdminUsername());
			query.setParameter("adminEmail", admin.getAdminEmail());
			query.setParameter("adminContact", admin.getAdminContact());
			query.setParameter("adminPwd", admin.getAdminPwd());
			int res = query.executeUpdate();
			if (res > 0) {
				return true;
			}
			return false;
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.dao.Dao#getAdminByEmail(java.lang.String)
	 */
	@Transactional
	public Admin getAdminByEmail(String adminEmail) throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Admin where adminEmail=:adminEmail");
			query.setParameter("adminEmail", adminEmail);
			return (Admin) query.getSingleResult();
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.dao.Dao#registerTenant(com.sagaranand.website.orm.
	 * Tenant)
	 */
	@Transactional
	public RegisterTenantResponse registerTenant(RegisterTenantRequest tenant) throws DalException {
		try {

			RegisterTenantResponse registerResp = new RegisterTenantResponse(false, false);

			// first, generate the tenantId and siteId
			String tenantId = UUID.randomUUID().toString();
			String siteId = UUID.randomUUID().toString();

			// first add to the Tenant Table
			Session session = this.sessionFactory.getCurrentSession();
			Query tenantQuery = session.createNativeQuery(
					"insert into Tenant(tenantId, tenantName, tenantEmail, tenantPwd) values(:tenantId, :tenantName, :tenantEmail, :tenantPwd)");
			tenantQuery.setParameter("tenantId", tenantId);
			tenantQuery.setParameter("tenantName", tenant.getTenantName());
			tenantQuery.setParameter("tenantEmail", tenant.getTenantEmail());
			tenantQuery.setParameter("tenantPwd", BCrypt.hashpw(tenant.getTenantPwd(), BCrypt.gensalt()));
			int addTenant = tenantQuery.executeUpdate();
			if (addTenant > 0) {
				registerResp.setTenantResp(true);
			}

			// now, add to the Site Table
			if (registerResp.isTenantResp()) {
				Query siteQuery = session.createNativeQuery(
						"insert into Site(siteId, siteName, tenantId) values(:siteId, :siteName, :tenantId)");
				siteQuery.setParameter("siteId", siteId);
				siteQuery.setParameter("siteName", tenant.getSiteName());
				siteQuery.setParameter("tenantId", tenantId);
				int addSite = siteQuery.executeUpdate();
				if (addSite > 0) {
					registerResp.setSiteResp(true);
				}
			}

			return registerResp;
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
	}

}
