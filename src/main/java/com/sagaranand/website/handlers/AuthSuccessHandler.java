/**
 * 
 */
package com.sagaranand.website.handlers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sagaranand.website.constants.ErrorCodes;
import com.sagaranand.website.constants.ErrorMesaages;
import com.sagaranand.website.model.SessionResponse;
import com.sagaranand.website.model.UserInfo;
import com.sagaranand.website.services.DaoService;

/**
 * @author sanand5
 *
 */
@Component("authSuccessHandler")
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	private DaoService daoService;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	private static final Logger logger = Logger.getLogger(AuthSuccessHandler.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		try {
			
			// on successful tenant authentication, move to the tenant admin page.
			// /tenant will show the admin console of the tenant.
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (userInfo != null) {
					System.out.println(userInfo.getName());
					redirectStrategy.sendRedirect(request, response, "/tenant");
				}
			} else {
				System.out.println("User is anonymous!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
