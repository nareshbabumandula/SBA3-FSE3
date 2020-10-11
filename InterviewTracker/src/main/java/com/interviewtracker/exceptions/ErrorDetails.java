package com.interviewtracker.exceptions;

import java.util.List;
import java.util.regex.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

	private String error_code;
	private String error_msg;
	public ErrorDetails(String error_code, String error_msg, List<String> str) {
		super();
		if(error_msg.contains("ValidationError")) {
			Matcher m = Pattern.compile("\\[([^)]+)\\]").matcher(error_msg);
			while(m.find()) {
			       String[] split=m.group(1).split(",");
			       if(split[0]!=null) {
			    	   this.error_code = split[0].split("=")[1];   
			       }
					this.error_msg = split[1].split("=")[1];
			     }
		}else {
			this.error_code = error_code;
			this.error_msg = error_msg;
		}
		
		//this.validationErrors = details;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

}
