/**
 * 
 */
package com.sagaranand.website.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
@Repository
public class AdminDaoImpl implements AdminDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.dao.Dao#getAllAdmins()
	 */
	public List<Admin> getAllAdmins() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Admin> adminList = session.createQuery("from Admin").getResultList();
		for (Admin a : adminList) {
			System.out.println(a.getAdminName() + " -> " + a.getAdminEmail());
		}
		return adminList;
	}

}
