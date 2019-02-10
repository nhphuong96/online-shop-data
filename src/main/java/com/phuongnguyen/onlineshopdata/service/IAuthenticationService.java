package com.phuongnguyen.onlineshopdata.service;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;

public interface IAuthenticationService {
	public ObjectWrapper<String> verify(String username, String password);
}
