/**
 * 
 */
package com.sagaranand.website.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.SynthesizedAnnotation;

/**
 * @author sanand5
 *
 */
public class ControllerFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(ControllerFilter.class);

	private FilterConfig filterConfig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;

			String servletPath = req.getServletPath();
			if (!startsWithStaticPath(servletPath.trim())) {
				
				System.out.println("this is from ControllerFilter: " + servletPath);
				
				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				res.setHeader("Pragma", "no-cache");
				res.setHeader("Expires", "0");
				res.setHeader("X-Frame-Options", "DENY");
			}

		} catch (Exception e) {
			System.out.println("I'm here too in FilterController!!");
			logger.error(e.getMessage(), e);
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	// For css, scripts, images, htmls, rest - login not needed
	private boolean startsWithStaticPath(String servletPath) {
		if (servletPath.trim().startsWith("/libraries/")) {
			return true;
		}
		return false;
	}

}
