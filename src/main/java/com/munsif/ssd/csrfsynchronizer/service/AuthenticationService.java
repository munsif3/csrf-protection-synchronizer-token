package com.munsif.ssd.csrfsynchronizer.service;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munsif.ssd.csrfsynchronizer.util.ApplicationConfig;
import com.munsif.ssd.csrfsynchronizer.util.HashUtil;

@Service
public class AuthenticationService {

	private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	private ApplicationConfig applicationConfig;

	public boolean isUserAuthenticated(String username, String password) {
		try {
			return (username.equalsIgnoreCase(applicationConfig.getUsername())
					&& HashUtil.getHashValue(password).equalsIgnoreCase(applicationConfig.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String generateSessionId(String username) {
		logger.debug("Generating session Id...");
		String sessionId = UUID.randomUUID().toString();

		return sessionId;

	}

	public String generateToken(String username) {
		logger.debug("Generating user anti-CSRF token...");

		String token = UUID.randomUUID().toString();

		return token;
	}
}
