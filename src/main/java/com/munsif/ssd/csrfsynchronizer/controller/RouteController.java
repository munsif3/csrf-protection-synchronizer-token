package com.munsif.ssd.csrfsynchronizer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.munsif.ssd.csrfsynchronizer.model.UserCredentials;
import com.munsif.ssd.csrfsynchronizer.service.AuthenticationService;

@Controller
public class RouteController {

	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/")
	public String showLoginPage(Model model, HttpServletRequest request) {

		// if (!authenticationService.isAuthenticated(request.getCookies())){
		model.addAttribute("login", new UserCredentials());
		return "redirect:/login";
		// } else {
		//// model.addAttribute("home", new FundTransfer());
		// return "redirect:/home";
		// }

	}

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("login", new UserCredentials());
		return "login";
	}
}
