package com.interviewtracker.exceptions;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "error")
public class ErrorResponse 
{
	 public ErrorResponse(List<String> details) {
	        super();
	        this.details = details;
	    }
	 
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
 
    private String code;
    
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;
 
    //Getter and setters
}
