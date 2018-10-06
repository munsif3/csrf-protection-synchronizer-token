package com.munsif.ssd.csrfsynchronizer.service;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munsif.ssd.csrfsynchronizer.model.CredentialStore;
import com.munsif.ssd.csrfsynchronizer.model.User;
import com.munsif.ssd.csrfsynchronizer.util.ApplicationConfig;
import com.munsif.ssd.csrfsynchronizer.util.HashUtil;

@Service
public class AuthenticationService {

	private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
	CredentialStore userCredentialsStore = new CredentialStore();

	@Autowired
	private ApplicationConfig applicationConfig;

	public boolean isValidUser(String username, String password) {
		logger.debug("Authenticating User...");
		try {
			return (username.equalsIgnoreCase(applicationConfig.getAuthUser())
					&& HashUtil.getHashValue(password).equalsIgnoreCase(applicationConfig.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isAuthenticated(Cookie[] cookies) {
		String session = "";
		String username = "";

		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("sessionID")) {
					session = cookie.getValue();
				} else if (cookie.getName().equals("username")) {
					username = cookie.getValue();
				}
			}
		}
		return (isUserSessionValid(username, session));
	}

	public boolean isUserSessionValid(String username, String sessionId) {
		logger.debug("Checking if user session is valid... " + username + ", " + sessionId);
		if (userCredentialsStore.findCredentials(username) != null) {
			return sessionId.equals(userCredentialsStore.findCredentials(username).getSessionID());
		}
		return false;
	}

	public String generateSessionId(String username) {
		logger.debug("Generating user session...");
		User credentials = userCredentialsStore.findCredentials(username);
		String sessionId = UUID.randomUUID().toString();
		String token = UUID.randomUUID().toString();

		credentials.setSessionID(sessionId);
		credentials.setToken(token);
		userCredentialsStore.addCredentials(credentials);

		logger.debug("Storing user session...");
		return sessionId;
	}

	public String sessionIdFromCookies(Cookie[] cookies) {
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("sessionID")) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public boolean validateCSRFToken(String sessionID, String token) {
		if (null != token) {
			return token.equals(userCredentialsStore.findTokenForSession(sessionID));
		}
		return false;
	}
}
