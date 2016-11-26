/**
 * 
 */
package com.sagaranand.website.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sagaranand.website.dao.AdminDao;
import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	private AdminDao dao;

	public void setDao(AdminDao dao) {
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
