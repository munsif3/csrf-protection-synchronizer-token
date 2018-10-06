package com.munsif.ssd.csrfsynchronizer.model;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CredentialStore {

	private Logger logger = LoggerFactory.getLogger(CredentialStore.class);
	private static HashMap<String, User> credentialsStore = new HashMap<>();
	private static HashMap<String, String> sessionTokenStore = new HashMap<>();

	public void seedCredentialStore() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("13A95C75B44F95EAD23F47F0BF10667E57B44EC5150180C8A39A39361CF56169");
		credentialsStore.put(user.getUsername(), user);
		logger.debug("Seeded the In-Memory HashMap...");
	}

	public void addCredentials(User user) {
		credentialsStore.put(user.getUsername(), user);
		if (user.getToken() != null) {
			sessionTokenStore.put(user.getSessionID(), user.getToken());
		}
	}

	public User findCredentials(String username) {
		return credentialsStore.get(username);
	}
	
	public String findTokenForSession(String sessionId) {
		return sessionTokenStore.get(sessionId);
	}
}
