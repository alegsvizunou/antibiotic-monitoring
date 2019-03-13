
package com.svizunov.antibioticmonitoring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value={"/", "/login", "/patients/hospitalSheetCharts",
	"/patients/hospitalSheetCharts/prescribtions"})
	public String index() {
		return "index";
	}

}
