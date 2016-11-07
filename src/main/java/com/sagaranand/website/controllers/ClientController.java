/**
 * 
 */
package com.sagaranand.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sanand5
 *
 */
@Controller
public class ClientController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

}
