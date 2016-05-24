package com.ysl.bonjour.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Configurator {
	private static final String BUNDLE_NAME = "com.ysl.bonjour.util.config"; //$NON-NLS-1$
	private static Logger log=Logger.getLogger(Configurator.class);

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Configurator() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			log.error("ERROR::Exception in getting config files",e);
			return '!' + key + '!';
		}
	}
}
