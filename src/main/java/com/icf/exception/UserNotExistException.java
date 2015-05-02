package com.icf.exception;

/**
 * This class is custom exception class used to handle the 
 * User already exists related exception.
 */
public class UserNotExistException extends Exception {
	private static final long serialVersionUID = 42342342344232L;

	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 * @param t Throwable exception.
	 */
	public UserNotExistException(String msg, Throwable t) {
		super(msg, t);		
	}
	
	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 */
	public UserNotExistException(String msg) {
		super(msg);		
	}
}
