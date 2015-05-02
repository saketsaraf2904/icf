package com.icf.exception;

/**
 * This class is custom exception class used to handle the Contact already
 * exists related exception.
 */
public class ContactAlreadyExistException extends Exception {
	private static final long serialVersionUID = 42342342344232L;

	/**
	 * Constructor with message and exception.
	 * 
	 * @param msg
	 *            Error message.
	 * @param t
	 *            Throwable exception.
	 */
	public ContactAlreadyExistException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * Constructor with message and exception.
	 * 
	 * @param msg
	 *            Error message.
	 */
	public ContactAlreadyExistException(String msg) {
		super(msg);
	}
}
