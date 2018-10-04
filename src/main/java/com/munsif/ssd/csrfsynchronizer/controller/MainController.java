package com.munsif.ssd.csrfsynchronizer.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.munsif.ssd.csrfsynchronizer.model.UserCredentials;
import com.munsif.ssd.csrfsynchronizer.service.AuthenticationService;;

@Controller
public class MainController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public String login(@ModelAttribute UserCredentials credentials, HttpServletResponse response) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		if (authenticationService.isUserAuthenticated(username, password)) {
			System.out.println("Logged in");
			return "redirect:/home";
		}
		
		System.out.println("failed login");
		return "redirect:/login?status=failed";
	}
}
