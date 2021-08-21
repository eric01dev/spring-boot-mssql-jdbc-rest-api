package com.example.demo.exception;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = -6285356466968464480L;

	public Class<?> objectClass;

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForbiddenException(String message) {
		super(message);
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}
}