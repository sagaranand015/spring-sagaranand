/**
 * 
 */
package com.sagaranand.website.validations;

/**
 * @author sanand5
 *
 */
public class ValidatorImpl {

	/**
	 * @param val
	 * @return boolean value stating where is string is empty or null
	 */
	public boolean validateString(String val) {
		if (val == null || val == "") {
			return false;
		}
		return true;
	}

	/**
	 * @param val
	 * @return true if the string is not null. False otherwise
	 */
	public boolean validateStringIsNull(String val) {
		if (val == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param val
	 * @return true if the String is not empty. False otherwise
	 */
	public boolean validateStringIsEmpty(String val) {
		if(val == "") {
			return false;
		}
		return true;
	}

}
