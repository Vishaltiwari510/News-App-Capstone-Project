package com.stackroute.favouriteservice.exception;

public class NewsNotFoundException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3621644867908155668L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "NewsNotFoundException [message=" + message + "]";
	}

	public NewsNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
}
