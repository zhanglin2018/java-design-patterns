package com.iluwatar.module;

import java.io.IOException;

public class LOGGER {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LOGGER.class);

	public static void info(String string) {
		log.info(string);
	}

	@SuppressWarnings("boxing")
	public static void info(String string, int identityHashCode) {
		log.info(string, identityHashCode);
	}

	public static void error(String string) {
		log.error(string);
	}

	public static void debug(String string) {
		log.debug(string);
	}

	public static void error(String string, IOException e) {
		log.error(string, e);
	}
}
