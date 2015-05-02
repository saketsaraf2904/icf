package com.icf.exception;

/**
 * This class is custom exception class used to handle the 
 * Authentication related exception.
 */
public class AuthenticationException extends Exception {

	private static final long serialVersionUID = 42342342344231L;

	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 * @param t Throwable exception.
	 */
	public AuthenticationException(String msg, Throwable t) {
		super(msg, t);		
	}
	
	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 */
	public AuthenticationException(String msg) {
		super(msg);		
	}
}
