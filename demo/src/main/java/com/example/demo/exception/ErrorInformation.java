package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ErrorInformation {
	private String message;
	private String exception;

	public ErrorInformation(String message, Exception ex) {
		this.message = message;
		if (ex != null) {
			this.exception = ex.getLocalizedMessage();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@JsonInclude(Include.NON_NULL)
	public String getException() {
		return exception;
	}
}