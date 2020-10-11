package com.interviewtracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class InterviewTrackerExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestException.class })
	public ErrorReponse handleBadRequest(BadRequestException e) {
		return new ErrorReponse(new ErrorDetails(e.getErrorCode(), e.getMessage(), null));
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ UnauthorizedException.class })
	public ErrorReponse handleUnauthorized(UnauthorizedException e) {
		return new ErrorReponse(new ErrorDetails(e.getErrorCode(), e.getMessage(), null));

	}
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ ForbiddenException.class })
	public ErrorReponse handleForbidden(ForbiddenException e) {
		return new ErrorReponse(new ErrorDetails(e.getErrorCode(), e.getMessage(), null));

	}
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorReponse assertionException(final IllegalArgumentException e) {
		return new ErrorReponse(new ErrorDetails(e.getMessage(), e.getMessage(), null));

	}
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(BadGatewayException.class)
	public ErrorReponse handleBadGateway(final BadGatewayException e) {
		return new ErrorReponse(new ErrorDetails(e.getMessage(), e.getMessage(), null));

	}
	@ResponseBody
	@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
	@ExceptionHandler(GatewayTimeoutException.class)
	public ErrorReponse handleGatewayTimeout(final GatewayTimeoutException e) {
		return new ErrorReponse(new ErrorDetails(e.getMessage(), e.getMessage(), null));

	}
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalServerException.class)
	public ErrorReponse handleInternalError(final InternalServerException e) {
		return new ErrorReponse(new ErrorDetails(e.getMessage(), e.getMessage(), null));

	}
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	@ExceptionHandler(UnsupportedException.class)
	public ErrorReponse handleNotSupported(final UnsupportedException e) {
		return new ErrorReponse(new ErrorDetails(e.getMessage(), e.getMessage(), null));

	}

}
