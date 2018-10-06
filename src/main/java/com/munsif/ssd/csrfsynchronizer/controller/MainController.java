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

import com.munsif.ssd.csrfsynchronizer.model.Blog;
import com.munsif.ssd.csrfsynchronizer.model.User;
import com.munsif.ssd.csrfsynchronizer.service.AuthenticationService;;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public String login(@ModelAttribute User credentials, HttpServletResponse response) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		if (authenticationService.isValidUser(username, password)) {
			logger.debug("Successfully authenticated/validated user...");
			Cookie sessionCookie = new Cookie("sessionID", authenticationService.generateSessionId(username));
			Cookie userCookie = new Cookie("username", username);
			response.addCookie(sessionCookie);
			response.addCookie(userCookie);
			return "redirect:/";
		}
		logger.debug("Failed to authenticate user...");
		return "redirect:/login?status=failed";
	}

	public String addBlog(@ModelAttribute Blog blog, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String blogToken = blog.getToken();

		String sessionId = authenticationService.sessionIdFromCookies(cookies);

		if (authenticationService.isAuthenticated(cookies)) {
			if (authenticationService.validateCSRFToken(sessionId, blogToken)) {
				logger.debug("Blog post successful. Session Token is validated...");
				return "redirect:/home?status=success";
			} else {
				logger.debug("Session Token is Invalid...");
			}
		}
		logger.debug("Session Cookie is Invalid...");
		return "redirect:/home?status=failed";
	}
}
