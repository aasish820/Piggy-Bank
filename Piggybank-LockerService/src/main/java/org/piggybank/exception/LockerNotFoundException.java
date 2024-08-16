package org.piggybank.exception;

public class LockerNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LockerNotFoundException(String message) {
		super(message);
	}

}
