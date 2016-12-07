/**
 * 
 */
package com.sagaranand.website.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import com.sagaranand.website.model.TenantInfo;
import com.sagaranand.website.model.User;
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

			TenantInfo tenantInfo = (TenantInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			// HttpSession session = request.getSession();
			// session.setAttribute("user", new User(auth.getName()));
			
			redirectStrategy.sendRedirect(request, response, "/admin");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
