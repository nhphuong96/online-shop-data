package com.phuongnguyen.onlineshopdata.exception;

public class SystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4411389789006126237L;
	
	public SystemException(String message)
	{
		super(message);
	}
	
	public SystemException(String message, Throwable e)
	{
		super(message, e);
	}

}
