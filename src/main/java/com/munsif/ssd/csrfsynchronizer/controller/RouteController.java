package com.munsif.ssd.csrfsynchronizer.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.munsif.ssd.csrfsynchronizer.model.Blog;
import com.munsif.ssd.csrfsynchronizer.model.User;
import com.munsif.ssd.csrfsynchronizer.service.AuthenticationService;

@Controller
public class RouteController {

	private Logger logger = LoggerFactory.getLogger(RouteController.class);

	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/")
	public String showLoginPage(Model model, HttpServletRequest request) {
		if (!authenticationService.isAuthenticated(request.getCookies())) {
			model.addAttribute("login", new User());
			return "redirect:/login";
		} else {
			model.addAttribute("home", new Blog());
			return "redirect:/home";
		}
	}

	@GetMapping("/login")
	public String showLogin(Model model) {
		logger.debug("Redirecting to Login...");
		model.addAttribute("login", new User());
		return "login";
	}

	@GetMapping("/home")
	public String showHomePage(Model model, HttpServletRequest request) {
		logger.debug("Redirecting to Home...");
		return "home";
	}
}
