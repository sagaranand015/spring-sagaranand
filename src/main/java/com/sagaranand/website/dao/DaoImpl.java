/**
 * 
 */
package com.sagaranand.website.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.id.UUIDGenerator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.model.Admin;
import com.sagaranand.website.model.AdminRequest;

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
	 * @see com.sagaranand.website.dao.Dao#getAllAdmins()
	 */
	@Transactional
	public List<Admin> getAllAdmins() throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Admin> adminList = session.createQuery("from Admin").getResultList();
			return adminList;
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sagaranand.website.dao.Dao#registerAdmin(com.sagaranand.website.model
	 * .Admin)
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean registerAdmin(Admin admin) throws DalException {
		try {
			
			System.out.println("I'm here in DaoImpl");
			
			Session session = this.sessionFactory.getCurrentSession();
//			admin.setUUID();
//			session.persist(admin);
			Query query = session.createNativeQuery("insert into Admin(AdminName, AdminUsername, AdminEmail, AdminContact, AdminPwd, salt) values(:adminName,:adminUsername,:adminEmail,:adminContact,:adminPwd,:salt)");
//			query.setParameter("adminId", UUID.randomUUID().toString());
			query.setParameter("adminName", admin.getAdminName());
			query.setParameter("adminUsername", admin.getAdminUsername());
			query.setParameter("adminEmail", admin.getAdminEmail());
			query.setParameter("adminContact", admin.getAdminContact());
			query.setParameter("adminPwd", admin.getAdminPwd());
			query.setParameter("salt", admin.getSalt());
			int res = query.executeUpdate();
			if(res > 0) {
				return true;
			}
			return false;
//			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
	}

}
