package com.interviewtracker.exceptions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorReponse extends BaseApiResponse {

	private ErrorDetails error;

	public ErrorReponse(ErrorDetails error) {
		super(new Date().getTime(), false);
		this.error = error;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}
}
