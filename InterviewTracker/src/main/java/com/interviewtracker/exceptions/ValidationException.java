package com.interviewtracker.exceptions;

public class ValidationException extends RuntimeException {

	public ValidationException() {

	}

	public ValidationException(ValidationError s) {
		// Call constructor of parent Exception
		super(s);
	}

//	  public ValidationException(ValidationError s, Throwable e) {
//	    // Call constructor of parent Exception
//	    super(s, e);
//	  }

}
