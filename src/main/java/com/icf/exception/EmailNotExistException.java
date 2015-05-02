package com.icf.exception;

/**
 * This class is custom exception class used to handle the 
 * Folder Not exists related exception.
 */
public class EmailNotExistException extends Exception {
	private static final long serialVersionUID = 42342342344232L;

	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 * @param t Throwable exception.
	 */
	public EmailNotExistException(String msg, Throwable t) {
		super(msg, t);		
	}
	
	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 */
	public EmailNotExistException(String msg) {
		super(msg);		
	}
}