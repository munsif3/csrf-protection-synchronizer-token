package com.munsif.ssd.csrfsynchronizer.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.munsif.ssd.csrfsynchronizer.model.Fund;
import com.munsif.ssd.csrfsynchronizer.model.Credentials;
import com.munsif.ssd.csrfsynchronizer.service.AuthenticationService;;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public String login(@ModelAttribute Credentials credentials, HttpServletResponse response) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		if (authenticationService.isUserAuthenticated(username, password)) {

			Cookie sessionCookie = new Cookie("sessionID", authenticationService.generateSessionId(username));

			response.addCookie(sessionCookie);
			logger.debug("Authentication Successful...");
			return "redirect:/home";
		}
		logger.debug("Authentication Failed...");
		return "redirect:/login?status=failed";
	}

	@PostMapping("/transfer")
	public String doTransfer(@ModelAttribute Fund fund, HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();

		logger.debug("In Home...", cookies.toString());

		return "redirect:/home?status=success";

	}
}
