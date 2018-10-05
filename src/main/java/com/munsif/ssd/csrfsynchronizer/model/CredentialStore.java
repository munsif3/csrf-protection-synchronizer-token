package com.munsif.ssd.csrfsynchronizer.model;

import java.util.HashMap;

import com.munsif.ssd.csrfsynchronizer.util.ProjectConstants;
import com.munsif.ssd.csrfsynchronizer.util.PropertyLoader;

/**
 *
 * @author munsif
 */
public class CredentialStore {

	private HashMap<String, Credentials> credentialsStoreMap;
	private static CredentialStore credentialsStore;

	private CredentialStore() {
		credentialsStoreMap = new HashMap<>();
	}

	/**
	 * Singleton to get the Credential store
	 */
	public static CredentialStore getCredentialStore() {
		if (credentialsStore == null) {
			synchronized (credentialsStore) {
				if (credentialsStore == null) {
					credentialsStore = new CredentialStore();
				}
			}
		}
		return credentialsStore;
	}

	/**
	 * Create initial user in store
	 */
	public void seedCredentialStoreMap() {
		Credentials credentials = new Credentials();
		String username = PropertyLoader.readProperty(ProjectConstants.PROPERTIES_FILE, ProjectConstants.PROPERTY_USERNAME);
		String password = PropertyLoader.readProperty(ProjectConstants.PROPERTIES_FILE, ProjectConstants.PROPERTY_PASSWORD);
		credentials.setUsername(username);
		credentials.setPassword(password);
		CredentialStore.getCredentialStore().saveCredentials(username, credentials);
	}

	public void saveCredentials(String username, Credentials credentials) {
		if (credentials != null && credentialsStoreMap.get(username) != null) {
			credentialsStoreMap.replace(username, credentials);
		} else {
			credentialsStoreMap.put(username, credentials);
		}
	}

    public void addSessionToken(String session, String token){

        // If a token exists, discard it and create a new one
        if(sessions.get(session) != null){
            sessions.replace(session, token);
        } else {
            sessions.put(session, token);
        }
    }
}
