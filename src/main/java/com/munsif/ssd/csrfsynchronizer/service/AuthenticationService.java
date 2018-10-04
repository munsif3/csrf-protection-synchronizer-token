package com.munsif.ssd.csrfsynchronizer.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munsif.ssd.csrfsynchronizer.util.ApplicationConfig;
import com.munsif.ssd.csrfsynchronizer.util.HashUtil;

@Service
public class AuthenticationService {

	@Autowired
	private ApplicationConfig applicationConfig;

	public boolean isUserAuthenticated(String username, String password) {
		System.out.println("applicationConfig.getUsername() " + applicationConfig.getUsername());
		System.out.println("username " + username);
		System.out.println("applicationConfig.getPassword() " + applicationConfig.getPassword());
		System.out.println("password " + password);
		try {
			System.out.println("XXX " + username.equalsIgnoreCase(applicationConfig.getUsername()));
			System.out.println("YYY " + HashUtil.getHashValue(password).equalsIgnoreCase(applicationConfig.getPassword()));
			return (username.equalsIgnoreCase(applicationConfig.getUsername())
					&& HashUtil.getHashValue(password).equalsIgnoreCase(applicationConfig.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}

}
