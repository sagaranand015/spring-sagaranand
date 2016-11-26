/**
 * 
 */
package com.sagaranand.website.dao;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sagaranand.website.exceptions.DalException;

/**
 * @author sanand5
 *
 */
@Repository
public class GeneralDaoImpl implements GeneralDao {

	private static final Logger logger = Logger.getLogger(GeneralDaoImpl.class);

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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DalException(e.getMessage());
		}
		return false;
	}

}
