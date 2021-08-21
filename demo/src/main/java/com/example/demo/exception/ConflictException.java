package com.example.demo.exception;

public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = -5053537955090445275L;

	public Class<?> objectClass;

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConflictException(String message) {
		super(message);
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}
}