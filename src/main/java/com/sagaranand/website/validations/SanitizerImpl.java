/**
 * 
 */
package com.sagaranand.website.validations;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

/**
 * @author sanand5
 *
 */
public class SanitizerImpl {

	public String sanitizeForBlocksAndFormatting(String val) {
		PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);
		return policy.sanitize(val);
	}
	
}
