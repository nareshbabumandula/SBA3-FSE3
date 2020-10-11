package com.comman;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.interviewtracker.exceptions.BaseApiResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessfulReponse extends BaseApiResponse{
	
	private Object response;

	public SuccessfulReponse(Object response) {
		super(new Date().getTime(),true);
		this.response=response;
	}

	public Object getResponse() {
		return response;
	}

}
