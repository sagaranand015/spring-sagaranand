/**
 * 
 */
package com.sagaranand.website.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sagaranand.website.constants.Constants;
import com.sagaranand.website.dao.AdminDao;
import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.model.Admin;

/**
 * @author sanand5
 *
 */
@Service
public class AdminServiceImpl implements AdminService, AuthenticationProvider {

	private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);

	private AdminDao adminDao;

	public void setDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sagaranand.website.services.AdminService#getAllAdmins()
	 */
	@Transactional
	public List<Admin> getAllAdmins() throws ServiceException, DalException {
		try {
			return this.adminDao.getAllAdmins();
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
			return this.adminDao.getAdminByUsername(username);
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	// /* (non-Javadoc)
	// * @see
	// org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	// */
	// public Authentication authenticate(Authentication authentication) throws
	// AuthenticationException {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /* (non-Javadoc)
	// * @see
	// org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	// */
	// public boolean supports(Class<?> authentication) {
	// // TODO Auto-generated method stub
	// return false;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {

			List<Admin> adminList = adminDao.getAllAdmins();
			for (Admin a : adminList) {
				System.out.println(a.getAdminEmail());
			}

			String name = authentication.getName();
			String password = authentication.getCredentials().toString();
			if (name.equals("admin") && password.equals("admin")) {
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				grantedAuths.add(new SimpleGrantedAuthority(Constants.ADMIN_ACCESS));
				return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
			}
		} catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * supports(java.lang.Class)
	 */
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
