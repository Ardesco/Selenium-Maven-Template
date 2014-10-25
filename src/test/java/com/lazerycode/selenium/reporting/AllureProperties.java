package com.lazerycode.selenium.reporting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureProperties {

	public static void create() {
		try {
			Properties props = new Properties();
			FileOutputStream fos = new FileOutputStream("target/allure-results/environment.properties");

			props.setProperty("Browser",
					System.getProperty("browser"));

			props.store(fos,
					"See https://github.com/allure-framework/allure-core/wiki/Environment");

			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
