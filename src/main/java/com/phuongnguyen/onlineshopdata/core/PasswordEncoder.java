package com.phuongnguyen.onlineshopdata.core;

public final class PasswordEncoder {
	
//	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private PasswordEncoder()
	{
	}
	
	public static String encode(String rawString)
	{
//		return encoder.encode(rawString);
		return rawString;
	}
}
