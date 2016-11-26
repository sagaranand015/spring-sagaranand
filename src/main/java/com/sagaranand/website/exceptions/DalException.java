/**
 * 
 */
package com.sagaranand.website.exceptions;

/**
 * @author sanand5
 *
 */
public class DalException extends Exception {

	private Integer errorCode;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public DalException() {
		super();
	}

	public DalException(Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public DalException(String message) {
		super(message);
	}

}
