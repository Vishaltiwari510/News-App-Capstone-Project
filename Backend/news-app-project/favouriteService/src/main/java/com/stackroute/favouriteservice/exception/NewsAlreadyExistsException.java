package com.stackroute.favouriteservice.exception;

public class NewsAlreadyExistsException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "NewsAlreadyExistsException [message=" + message + "]";
	}

	public NewsAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
