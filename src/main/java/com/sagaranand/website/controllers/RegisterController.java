/**
 * 
 */
package com.sagaranand.website.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sagaranand.website.client.GenericClient;
import com.sagaranand.website.constants.ApiEndpoints;
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.model.RegisterTenantRequest;
import com.sagaranand.website.model.RegisterTenantResponse;
import com.sagaranand.website.services.DaoService;
import com.sagaranand.website.utilities.MailUtilities;
import com.sagaranand.website.validations.SanitizerImpl;
import com.sagaranand.website.validations.ValidatorImpl;

/**
 * @author sanand5
 *
 */
@Controller
public class RegisterController {

	private static final Logger logger = Logger.getLogger(RegisterController.class);

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
	 * @return the register.jsp page
	 */
	@RequestMapping(value = ApiEndpoints.REGISTER_ENDPOINT)
	public String register(@RequestParam(value = "valid", required = false) String valid, Model model) {
		if (valid != null && valid.equals("false")) {
			model.addAttribute("valid", false);
		}
		return ApiEndpoints.REGISTER_ENDPOINT;
	}

	/**
	 * @param email
	 * @param name
	 * @param password
	 * @param confirmPassword
	 * @param siteName
	 * @param model
	 * @return the user page for given siteName on successful registration.
	 *         Otherwise, error on Registration
	 */
	@RequestMapping(value = ApiEndpoints.REGISTER_ENDPOINT + ApiEndpoints.ROOT
			+ ApiEndpoints.REGISTER_SUBMIT, method = RequestMethod.POST)
	public String registerSubmit(@RequestParam("txt-email") String email, @RequestParam("txt-name") String name,
			@RequestParam("txt-password") String password, @RequestParam("txt-confirm-password") String confirmPassword,
			@RequestParam("txt-site") String siteName, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		try {
			// validation of the request parameters
			if (!validator.validateEmail(email) || !validator.validatePassword(password)
					|| !validator.validatePassword(confirmPassword) || !validator.validateUsername(siteName)
					|| !validator.validateString(name)) {
				return "redirect:" + ApiEndpoints.ROOT + ApiEndpoints.REGISTER_ENDPOINT + "?valid=false";
			}

			// check if the password and confirm Password match here
			if (!validator.checkPasswordMatch(password, confirmPassword)) {
				return "redirect:" + ApiEndpoints.ROOT + ApiEndpoints.REGISTER_ENDPOINT + "?valid=false";
			}

			// call the register functionality in the dal layer
			RegisterTenantResponse registerResp = this.daoService
					.registerTenant(new RegisterTenantRequest(email, name, password, siteName));
			if (!registerResp.isTenantResp() && !registerResp.isSiteResp()) {
				return "redirect:" + ApiEndpoints.ROOT + ApiEndpoints.REGISTER_ENDPOINT + "?valid=false";
			}

			return "redirect:http://" + siteName + ".sagaranand.com/";
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return "redirect:" + ApiEndpoints.ROOT + ApiEndpoints.REGISTER_ENDPOINT + "?valid=false";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "redirect:" + ApiEndpoints.ROOT + ApiEndpoints.REGISTER_ENDPOINT + "?valid=false";
		}
	}

}
