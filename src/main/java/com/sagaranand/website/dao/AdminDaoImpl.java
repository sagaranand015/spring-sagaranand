/**
 * 
 */
package com.sagaranand.website.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
@Repository
public class AdminDaoImpl implements AdminDao {

	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.dao.Dao#getAllAdmins()
	 */
	public List<Admin> getAllAdmins() throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Admin> adminList = session.createQuery("from Admin").getResultList();
			for (Admin a : adminList) {
				System.out.println(a.getAdminName() + " -> " + a.getAdminEmail());
			}
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
	public Admin getAdminByUsername(String username) throws DalException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Admin where adminUsername=:adminUsername");
			query.setParameter("adminUsername", username);
			return (Admin) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
