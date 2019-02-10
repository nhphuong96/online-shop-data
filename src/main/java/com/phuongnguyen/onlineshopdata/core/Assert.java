package com.phuongnguyen.onlineshopdata.core;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.phuongnguyen.onlineshopdata.exception.ValidationException;

public final class Assert {
	private Assert() {}
	
	public static void assertNotNull(Object obj) throws ValidationException 
	{
		if (obj == null) 
		{
			throw new ValidationException("Object must not be null.");
		}
	}
	
	public static void assertNotNull(Object obj, String objectName) throws ValidationException 
	{
		if (StringUtils.isEmpty(objectName))
		{
			assertNotNull(obj);
		}
		if (obj == null) 
		{
			throw new ValidationException(objectName + " must not be null.");
		}
	}
	
	public static void assertNotEmpty(String charSequence) throws ValidationException
	{
		if (StringUtils.isEmpty(charSequence))
		{
			throw new ValidationException("String must not be null or empty.");
		}
	}
	
	public static void assertNotEmpty(List<Object> objList) throws ValidationException
	{
		if (CollectionUtils.isEmpty(objList))
		{
			throw new ValidationException("List must not be null or empty.");
		}
	}
}
