package com.phuongnguyen.onlineshopdata.core;

public class ObjectWrapper<T extends Object> {

	private boolean isSuccess;
	private T body;
	private String message;

	public ObjectWrapper(T object) {
		this.body = object;
		this.isSuccess = true;
		this.message = "success";
	}
	
	public ObjectWrapper(T object, String message)
	{
		this.body = object;
		this.isSuccess = true;
		this.message = message;
	}

	public ObjectWrapper(T object, boolean isSuccess, String message) {
		this.body = object;
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public ObjectWrapper(T object, boolean isSuccess)
	{
		this.body = object;
		this.isSuccess = isSuccess;
		this.message = "";
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
