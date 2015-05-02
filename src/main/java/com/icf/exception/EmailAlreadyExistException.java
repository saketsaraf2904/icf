package com.icf.exception;

/**
 * This class is custom exception class used to handle the 
 * Email already exists related exception.
 */
public class EmailAlreadyExistException extends Exception {
	private static final long serialVersionUID = 42342342344232L;

	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 * @param t Throwable exception.
	 */
	public EmailAlreadyExistException(String msg, Throwable t) {
		super(msg, t);		
	}
	
	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 */
	public EmailAlreadyExistException(String msg) {
		super(msg);		
	}
}
