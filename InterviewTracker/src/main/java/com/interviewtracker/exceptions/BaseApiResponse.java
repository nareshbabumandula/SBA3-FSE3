package com.interviewtracker.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseApiResponse {

	private long timestamp;
	private boolean success;
	private long resultSize;
	public BaseApiResponse(long timestamp, boolean success) {
		super();
		this.timestamp = timestamp;
		this.success = success;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public long getResultSize() {
		return resultSize;
	}
	public void setResultSize(long resultSize) {
		this.resultSize = resultSize;
	}

}

