/**
 * 
 */
package com.sagaranand.website.providers;

import java.util.ArrayList;


import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sagaranand.website.constants.Constants;
import com.sagaranand.website.exceptions.ServiceException;
import com.sagaranand.website.model.UserInfo;
import com.sagaranand.website.orm.Tenant;
import com.sagaranand.website.services.DaoService;
import com.sagaranand.website.validations.ValidatorImpl;

/**
 * @author sanand5
 *
 */
@Component
public class LoginAuthProvider implements AuthenticationProvider {

	@Autowired
	private DaoService daoService;

	@Autowired
	private ValidatorImpl validator;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	public void setValidator(ValidatorImpl validator) {
		this.validator = validator;
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
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();

			String email = authentication.getName().trim();
			final String password = authentication.getCredentials().toString().trim();
			if (!validator.validateEmail(email) || !validator.validatePassword(password)) {
				return null;
			}
			Tenant tenant = daoService.getTenantByEmail(email);
			if (tenant.getTenantEmail().equals(email) && BCrypt.checkpw(password, tenant.getTenantPwd())) {
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				grantedAuths.add(new SimpleGrantedAuthority(Constants.USER_ACCESS));
				return new UsernamePasswordAuthenticationToken(
						new UserInfo(tenant.getTenantId(), tenant.getTenantName(), tenant.getTenantEmail(),
								tenant.getTenantContact(), tenant.getTenantType()),
						password, grantedAuths);
			}
		} catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (ServiceException e) {
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
