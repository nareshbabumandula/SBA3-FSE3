package com.interviewtracker.model;

import java.io.Serializable;

public class AttendeeDTO implements Serializable{

	private String userId;
	
	private String interviewId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}

	

	
	
	
}
