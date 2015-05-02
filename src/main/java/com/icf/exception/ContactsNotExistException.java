package com.icf.exception;

/**
 * This class is custom exception class used to handle the 
 * Folder Not exists related exception.
 */
public class ContactsNotExistException extends Exception {
	private static final long serialVersionUID = 42342342344232L;

	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 * @param t Throwable exception.
	 */
	public ContactsNotExistException(String msg, Throwable t) {
		super(msg, t);		
	}
	
	/**
	 * Constructor with message and exception.
	 * @param msg Error message.
	 */
	public ContactsNotExistException(String msg) {
		super(msg);		
	}
}

