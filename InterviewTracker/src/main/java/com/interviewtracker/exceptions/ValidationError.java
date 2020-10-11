package com.interviewtracker.exceptions;

public class ValidationError extends Exception{
	
	private String errorCode;
	private String errorMessage;
	
	public ValidationError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public ValidationError() {
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "ValidationError [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

}
