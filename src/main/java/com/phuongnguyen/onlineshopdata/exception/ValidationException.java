package com.phuongnguyen.onlineshopdata.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1964887370898448487L;
	
	public ValidationException(String message)
	{
		super(message);
	}
	
	public ValidationException(String message, Throwable e)
	{
		super(message, e);
	}
	
}
