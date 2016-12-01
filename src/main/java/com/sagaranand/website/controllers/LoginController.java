/**
 * 
 */
package com.sagaranand.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sagaranand.website.client.GenericClient;
import com.sagaranand.website.constants.ApiEndpoints;
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.services.DaoService;
import com.sagaranand.website.utilities.MailUtilities;
import com.sagaranand.website.validations.SanitizerImpl;
import com.sagaranand.website.validations.ValidatorImpl;

/**
 * @author sanand5
 *
 */
@Controller
public class LoginController {

	@Autowired
	private GenericClient client;

	@Autowired
	private MailImpl mail;

	@Autowired
	private MailUtilities mailUtilities;

	@Autowired
	private ValidatorImpl validator;

	@Autowired
	private SanitizerImpl sanitizer;

	@Autowired
	private DaoService daoService;
	
	/**
	 * the /login endpoint
	 * 
	 * @return The login page - login.jsp
	 */
	@RequestMapping(value = ApiEndpoints.LOGIN_ENDPOINT)
	public String login(@RequestParam(value = "valid", required = false) String valid,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "session", required = false) String session, Model model) {
		if (valid != null && valid.equals("false")) {
			model.addAttribute("valid", false);
		}
		if (logout != null && logout.equals("true")) {
			model.addAttribute("logout", true);
		}
		if (session != null && session.equals("false")) {
			model.addAttribute("session", false);
		}
		return ApiEndpoints.LOGIN_ENDPOINT;
	}
	
}
