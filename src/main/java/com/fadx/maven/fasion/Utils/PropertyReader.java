package com.fadx.maven.fasion.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyReader {

	static Properties properties = new Properties();
	static String dataConfig = "./src/test/resources/testdata/Data.properties";
	static String globalConfig = "./GlobalConfig.properties";

	public static String readDataProperty(String property) {
		InputStream inPropFile = null;
		try {

			inPropFile = new FileInputStream(dataConfig);
			properties.load(inPropFile);
		} catch (IOException e) {
		}
		String value = properties.getProperty(property);

		return value;
	}

	public static void writeDataProperty(String property, String value) {
		try {
			InputStream inPropFile = new FileInputStream(dataConfig);
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(dataConfig);
			properties.setProperty(property, value);
			properties.store(outPropFile, null);
			outPropFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readGlobalProperty(String property) {
		InputStream inPropFile = null;
		try {

			inPropFile = new FileInputStream(globalConfig);
			properties.load(inPropFile);
		} catch (IOException e) {
		}
		String value = properties.getProperty(property);

		return value;
	}

	public static void writeGlobalProperty(String property, String value) {
		try {
			InputStream inPropFile = new FileInputStream(globalConfig);
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(globalConfig);
			properties.setProperty(property, value);
			properties.store(outPropFile, null);
			outPropFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
