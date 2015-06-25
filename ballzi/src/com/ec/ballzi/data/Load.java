package com.ec.ballzi.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Load {
	private Properties prop;

	public Load() {
		prop = new Properties();
	}

	public Properties loadProp(String path) {
		try {
			prop.load(new FileInputStream(path));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}
}
