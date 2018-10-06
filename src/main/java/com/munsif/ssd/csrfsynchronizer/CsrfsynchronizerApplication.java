package com.munsif.ssd.csrfsynchronizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.munsif.ssd.csrfsynchronizer.model.CredentialStore;

@SpringBootApplication
public class CsrfsynchronizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsrfsynchronizerApplication.class, args);
		new CredentialStore().seedCredentialStore();
	}
}
