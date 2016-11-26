/**
 * 
 */
package com.sagaranand.website.providers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.sagaranand.website.constants.Constants;
import com.sagaranand.website.model.Admin;
import com.sagaranand.website.services.DaoService;

/**
 * @author sanand5
 *
 */
@Component
public class LoginAuthProvider implements AuthenticationProvider {

	@Autowired
	private DaoService daoService;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	private static final Logger logger = LoggerFactory.getLogger(LoginAuthProvider.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			
			System.out.println("In authentication: ");
			List<Admin> adminList = daoService.getAllAdmins();
			for(Admin a: adminList) {
				System.out.println("This is the admin: " + a.getAdminName());
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
