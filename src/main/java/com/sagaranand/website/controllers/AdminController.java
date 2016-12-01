/**
 * 
 */
package com.sagaranand.website.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sagaranand.website.client.GenericClient;
import com.sagaranand.website.constants.ApiEndpoints;
import com.sagaranand.website.constants.ErrorCodes;
import com.sagaranand.website.constants.ErrorMesaages;
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.model.ServiceResponse;
import com.sagaranand.website.orm.Admin;
import com.sagaranand.website.services.DaoService;
import com.sagaranand.website.utilities.MailUtilities;
import com.sagaranand.website.validations.SanitizerImpl;
import com.sagaranand.website.validations.ValidatorImpl;

/**
 * @author sanand5
 *
 */
@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

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
	 * the /admin endpoint for admin access portal
	 * 
	 * @return the admin page
	 */
	@RequestMapping(value = ApiEndpoints.ADMIN_ENDPOINT)
	public String adminPage() {
		return ApiEndpoints.ADMIN_ENDPOINT;
	}

	/**
	 * 
	 * @param admin
	 * @return the response containing the success/fail response for Admin
	 *         Record insertion
	 */
	@RequestMapping(value = ApiEndpoints.ADMIN_ENDPOINT + ApiEndpoints.ROOT
			+ ApiEndpoints.ADMIN_ADD, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ServiceResponse> registerAdmin(@RequestBody Admin admin) {
		try {

			// get all the other parameters of the Admin Request
			if (!validator.validateStringIsNull(admin.getAdminName())) {
				admin.setAdminName("");
			}
			if (!validator.validateStringIsNull(admin.getAdminContact())) {
				admin.setAdminContact("");
			}

			// necessary validations required for Username and Email
			if (!validator.validateEmail(admin.getAdminEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			} else if (!validator.validateUsername(admin.getAdminUsername())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			} else if (!validator.validatePassword(admin.getAdminPwd())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			} else if (!validator.validateStringContent(admin.getAdminName())
					|| !validator.validateStringContent(admin.getAdminContact())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ServiceResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
			}

			if (!this.daoService.registerAdmin(admin)) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
						new ServiceResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR));
			}

			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new ServiceResponse(ErrorCodes.OK, ErrorMesaages.OK));
		} catch (DalException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.DB_OPERATION_FAILED, ErrorMesaages.DB_OPERATION_FAILED));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR));
		}
	}

}
