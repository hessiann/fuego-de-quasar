package com.meli.quasar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		return "swagger/index";
	}

	@GetMapping("/swagger")
	public String swagger(HttpServletRequest request) {
		return "swagger/index";
	}

}
