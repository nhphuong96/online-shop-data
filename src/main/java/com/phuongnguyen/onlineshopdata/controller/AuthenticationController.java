package com.phuongnguyen.onlineshopdata.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.dto.UserDto;
import com.phuongnguyen.onlineshopdata.service.IAuthenticationService;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
	
	@Autowired
	private IAuthenticationService authenticationService;
	
	@PostMapping("/login")
	public ObjectWrapper<String> login(@RequestBody UserDto user, HttpServletRequest req, HttpServletResponse res)
	{
		ObjectWrapper<String> result = authenticationService.verify(user.getUsername(), user.getPassword());
		if (StringUtils.isNotEmpty(result.getBody()))
		{
			HttpSession session = req.getSession(true);
			if (session != null)
			{
				session.setAttribute("token", result.getBody());
				session.setMaxInactiveInterval(30 * 60);				
				result.setMessage("Login successful.");
			}
		}
		return result;
	}
	
	@GetMapping("/logoff")
	public ObjectWrapper<String> logOff(HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		if (session != null)
		{
			session.invalidate();
			return new ObjectWrapper<String>(null, true, "logoff successful.");
		}
		return new ObjectWrapper<String>(null, false, "logoff fail.");
	}
	
	@GetMapping("/verify")
	@Deprecated
	public ObjectWrapper<String> verify(HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		if (session != null)
		{
			String authenticatedToken = (String) session.getAttribute("token");
			if (StringUtils.isNotEmpty(authenticatedToken))
			{
				String token = req.getHeader("token");
				if (StringUtils.isNotEmpty(token) && token.equals(authenticatedToken))
				{
					return new ObjectWrapper<String>(null, false, "valid");
				}
			}
		}
		return new ObjectWrapper<String>(null, false, "invalid");
	}
	
}
