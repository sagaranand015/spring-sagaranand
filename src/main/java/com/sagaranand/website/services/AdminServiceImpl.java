/**
 * 
 */
package com.sagaranand.website.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sagaranand.website.dao.Dao;
import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

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
	public List<Admin> getAllAdmins() {
		return this.dao.getAllAdmins();
	}

}
