package com.zz.rest.demo.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtility {
	private static final String BUSINESS_LOG_NAME = "tc.businessLog";
	private static final String ERROR_LOG_NAME = "tc.errorLog";

	private LogUtility() {
	}

	public static Logger businessLog() {
		return LoggerFactory.getLogger(BUSINESS_LOG_NAME);
	}

	public static Logger errorLog() {
		return LoggerFactory.getLogger(ERROR_LOG_NAME);
	}
}
