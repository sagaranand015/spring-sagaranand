/**
 * 
 */
package com.sagaranand.website.client;

import com.sagaranand.website.constants.ErrorCodes;
import com.sagaranand.website.constants.ErrorMesaages;
import com.sagaranand.website.model.ServiceResponse;

/**
 * @author sanand5
 *
 */
public class GenericClient {

	public GenericClient() {
		super();
	}
	
	/**
	 * 
	 * @return the service response based on check on db 
	 */
	public ServiceResponse getServiceStatus() {
		return new ServiceResponse(ErrorCodes.OK, ErrorMesaages.OK);
	}
	
}
