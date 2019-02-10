package com.phuongnguyen.onlineshopdata.service.impl;

import java.security.SecureRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.core.PasswordEncoder;
import com.phuongnguyen.onlineshopdata.model.User;
import com.phuongnguyen.onlineshopdata.service.IAuthenticationService;
import com.phuongnguyen.onlineshopdata.service.IUserService;

@Service
@Transactional
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom secureRandom = new SecureRandom();
	
	@Autowired
	private IUserService userService;
	
	@Override
	public ObjectWrapper<String> verify(String username, String password) {
		User user = userService.findByUsername(username);
		if (user != null)
		{
			if (user.getPassword().equals(PasswordEncoder.encode(password)))
			{
				String token = generateToken();
				return new ObjectWrapper<String>(token, true, "Username and password are correct.");
			}
			else
			{
				return new ObjectWrapper<String>(null, false, "Username or password is incorrect. Please try again.");
			}
		}
		return new ObjectWrapper<String>(null, false, "User is not exist.");
	}
	
	private static String generateToken()
	{
		StringBuilder sb = new StringBuilder(32);
		for(int i = 0; i < 32; i++)
		{
			sb.append(AB.charAt(secureRandom.nextInt(AB.length())));
		}
		return sb.toString();	
	}
	
}
