package com.wordl.testoffice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class MyException extends Exception{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public MyException(String message) {
		
		logger.error("Error capturado: {}", message);
	}

}
