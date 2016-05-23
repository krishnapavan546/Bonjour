package com.ysl.bonjour.exception;


/**
 * @author pku134
 *
 */
public class BonjourException extends Exception {

	private static final long serialVersionUID = 1L;

	public BonjourException() {
		super();
		
	}

	/**
	 * @param message
	 */
	public BonjourException(String message) {
		super(message);		
	}
	
}
