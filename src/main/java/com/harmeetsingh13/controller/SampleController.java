/**
 * 
 */
package com.harmeetsingh13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 *
 */
@Controller
@RequestMapping(value="/")
public class SampleController {

	@RequestMapping(value="/tiles-url")
	public String tilesTest() {
		System.out.println("This is Tiles Controller");
		
		return "dashboard";
	}
	
	@RequestMapping(value="/thm-url")
	public String thmTest() {
		System.out.println("This is Thymeleaf Controller");
		
		return "thymeleaf/thm-dashboard";
	}
	
	@RequestMapping(value="/jsp-url")
	public String jspTest() {
		System.out.println("This is JSP Controller");
		
		return "jsp-view";
	}
}
