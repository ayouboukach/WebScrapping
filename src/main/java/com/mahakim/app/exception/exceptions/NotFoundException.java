package com.mahakim.app.exception.exceptions;

public class NotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7312741677246882821L;
	public NotFoundException(String message) {
        super(message);
    }
}
