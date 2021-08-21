package com.example.demo.exception;

public class ContentNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 8279504441735358462L;

	public Class<?> objectClass;

	public ContentNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContentNotSupportedException(String message) {
		super(message);
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}
}