/**
 * 
 */
package com.sagaranand.website.exceptions;

/**
 * @author sanand5
 *
 */
public class ServiceException extends Exception {

	private Integer errorCode;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public ServiceException() {
		super();
	}

	public ServiceException(Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ServiceException(String message) {
		super(message);
	}

}
