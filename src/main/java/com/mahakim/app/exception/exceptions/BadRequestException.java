package com.mahakim.app.exception.exceptions;

public class BadRequestException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3457965106230601305L;
	public BadRequestException(String message) {
        super(message);
    }
}
