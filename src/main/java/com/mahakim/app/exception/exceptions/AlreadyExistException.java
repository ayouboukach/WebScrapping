package com.mahakim.app.exception.exceptions;

public class AlreadyExistException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7467337429379595623L;
	public AlreadyExistException(String message) {
        super(message);
    }
}
