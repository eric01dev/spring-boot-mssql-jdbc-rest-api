package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE) // 415
	@ExceptionHandler(ContentNotSupportedException.class)
	@ResponseBody
	public ErrorInformation handleNotSupported(ContentNotSupportedException e) {
		return new ErrorInformation("Content is not supported", e);
	}

	@ResponseStatus(HttpStatus.CONFLICT) // 409
	@ExceptionHandler(ConflictException.class)
	@ResponseBody
	public ErrorInformation handleConflict(ConflictException e) {
		return new ErrorInformation("Conflict", e);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseBody
	public ErrorInformation handleNotFound(ObjectNotFoundException e) {
		return new ErrorInformation("Not found", e);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN) // 403
	@ExceptionHandler(ForbiddenException.class)
	@ResponseBody
	public ErrorInformation handleForbidden(ForbiddenException e) {
		return new ErrorInformation("Forbidden", e);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ErrorInformation handleIllegal(IllegalArgumentException e) {
		return new ErrorInformation("Bad request", e);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseBody
	public ErrorInformation handleBadMessageConversion(HttpMessageConversionException e) {
		return new ErrorInformation("Bad request", e);
	}

	// Fall back
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorInformation handleOtherException(Exception e) {
		return new ErrorInformation("Internal server error", e);
	}
}