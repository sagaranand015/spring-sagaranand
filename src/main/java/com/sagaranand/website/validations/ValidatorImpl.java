/**
 * 
 */
package com.sagaranand.website.validations;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;

/**
 * @author sanand5
 *
 */
public class ValidatorImpl {

	Validator validator;

	/**
	 * Constructor for initializing the ESAPI validator and other properties
	 */
	public ValidatorImpl() {
		validator = ESAPI.validator();
		if (validator == null) {
			System.out.println("ESAPI Validator has not been intialized. Please check again.");
		}
	}

	/**
	 * @param val
	 * @return boolean value stating where is string is empty or null
	 */
	public boolean validateString(String val) {
		return (val == "" || val == "") ? false : true;
	}

	/**
	 * @param val
	 * @return true if the string is not null. False otherwise
	 */
	public boolean validateStringIsNull(String val) {
		return (val == null) ? false : true;
	}

	/**
	 * @param val
	 * @return true if the String is not empty. False otherwise
	 */
	public boolean validateStringIsEmpty(String val) {
		return (val == "") ? false : true;
	}

	/**
	 * @param val
	 * @return true if the email is validated
	 */
	public boolean validateEmail(String val) {
		return validator.isValidInput("email", val, "Email", val.length(), false);
	}
	
	/**
	 * 
	 * @param val
	 * @return true if the String passed is safe to be used
	 */
	public boolean validateStringContent(String val) {
		return validator.isValidInput("stringContent", val, "SafeString", val.length(), false);
	}

}
