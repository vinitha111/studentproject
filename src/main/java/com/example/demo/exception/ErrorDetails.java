package com.example.demo.exception;

import java.util.Date;

public class ErrorDetails {
	
private Date date;
private String message;
private String uri;	

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ErrorDetails(Date date, String message, String uri) {
		this.date = date;
}
}
