/**
 * 
 */
package com.sagaranand.website.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.sagaranand.website.client.GenericClient;
import com.sagaranand.website.constants.ApiEndpoints;
import com.sagaranand.website.constants.ErrorCodes;
import com.sagaranand.website.constants.ErrorMesaages;
import com.sagaranand.website.core.MailImpl;
import com.sagaranand.website.exceptions.DalException;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.orm.Admin;
import com.sagaranand.website.model.ContactRequest;
import com.sagaranand.website.model.ContactResponse;
import com.sagaranand.website.model.ServiceResponse;
import com.sagaranand.website.model.SessionResponse;
import com.sagaranand.website.model.User;
import com.sagaranand.website.services.DaoService;
import com.sagaranand.website.utilities.MailUtilities;
import com.sagaranand.website.validations.SanitizerImpl;
import com.sagaranand.website.validations.ValidatorImpl;

/**
 * @author sanand5
 *
 */
@Controller
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

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
	 * The / Endpoint
	 * 
	 * @return home.jsp - the root page
	 */
	@RequestMapping(value = ApiEndpoints.ROOT)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return ApiEndpoints.ROOTPAGE;
	}

	/**
	 * The /home Endpoint
	 * 
	 * @return home.jsp - the root page
	 */
	@RequestMapping(value = ApiEndpoints.ROOTPAGE)
	public String home() {
		return ApiEndpoints.ROOTPAGE;
	}

	/**
	 * The /status Endpoint
	 * 
	 * @return Response code for service status
	 */
	@RequestMapping(value = ApiEndpoints.STATUS_ENDPOINT, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ServiceResponse> getStatus(HttpServletRequest request) {
		try {
			if (!daoService.checkdbStatus()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.body(new ServiceResponse(ErrorCodes.DB_FAILED, ErrorMesaages.DB_FAILED));
			}
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new ServiceResponse(ErrorCodes.OK, ErrorMesaages.OK));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.DB_OPERATION_FAILED, ErrorMesaages.DB_OPERATION_FAILED));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR));
		}
	}

	/**
	 * 
	 * @param request
	 * @return the SessionResponse containing session information
	 */
	@RequestMapping(value = "session", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<SessionResponse> getSession(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session != null) {
				User loggedInUser = (User) session.getAttribute("user");
				if (loggedInUser != null) {
					return ResponseEntity.status(HttpStatus.OK.value())
							.body(new SessionResponse(ErrorCodes.OK, ErrorMesaages.OK, loggedInUser));
				}
			}
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new SessionResponse(ErrorCodes.UNAUTHORIZED, ErrorMesaages.UNAUTHORIZED, null));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
					new SessionResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorMesaages.INTERNAL_SERVER_ERROR, null));
		}
	}

}
