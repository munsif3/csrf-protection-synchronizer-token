package com.munsif.ssd.csrfsynchronizer.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

	private static Properties properties;
	private static InputStream input = null;

	public PropertyLoader() {

	}

	public static String readProperty(String file, String property) {
		try {
			input = new FileInputStream(file);
			properties.load(input);
			return properties.getProperty(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
