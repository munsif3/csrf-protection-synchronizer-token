package com.munsif.ssd.csrfsynchronizer.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Value("${csrfapp.username}")
	private String username;
	
	@Value("${csrfapp.password}")
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
