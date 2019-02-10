package com.phuongnguyen.onlineshopdata.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuongnguyen.onlineshopdata.core.Assert;
import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.core.PasswordEncoder;
import com.phuongnguyen.onlineshopdata.exception.SystemException;
import com.phuongnguyen.onlineshopdata.exception.ValidationException;
import com.phuongnguyen.onlineshopdata.model.User;
import com.phuongnguyen.onlineshopdata.repository.UserRepository;
import com.phuongnguyen.onlineshopdata.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public ObjectWrapper<List<User>> findAll() {
		List<User> userList = repo.findAll();
		if (userList != null)
		{
			return new ObjectWrapper<List<User>>(userList);
		}
		return new ObjectWrapper<List<User>>(null, false);
	}

	@Override
	public ObjectWrapper<Integer> insert(User user) {
		try
		{
			if (user == null) {
				throw new SystemException("User must not be null or empty");
			}
			Assert.assertNotNull(user.getUsername(), "Username");
			Assert.assertNotNull(user.getPassword(), "Password");
			
			User existingUser = repo.findByUsername(user.getUsername());
			if (existingUser != null)
			{
				return new ObjectWrapper<Integer>(0, false, "Username has already existed");
			}
			
			String hashedPassword = PasswordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			User savedUser = repo.save(user);
			if (savedUser.getId() != 0)
			{
				return new ObjectWrapper<Integer>(savedUser.getId(), true, "Created user successfully.");
			}
		}
		catch (SystemException | ValidationException e)
		{
			return new ObjectWrapper<Integer>(0, false, e.getMessage());
		}
		return new ObjectWrapper<Integer>(0, false, "Error occurred while trying to persist user.");
	}

	@Override
	public ObjectWrapper<Integer> deleteById(int id) {
		try
		{
			repo.deleteById(id);
			return new ObjectWrapper<Integer>(id, "Delete user successfully.");
		}
		catch (Exception e)
		{
			logger.error("Error occurred while deleting user id = " + id);
		}
		return new ObjectWrapper<Integer>(0, false);
	}

	@Override
	public ObjectWrapper<Integer> update(User updatingUser) {
		if (updatingUser.getId() == 0)
		{
			logger.error("Invalid user Id");
			return new ObjectWrapper<Integer>(0, false, "Invalid user Id: " + updatingUser.getId());
		}
		else
		{
			User savedUser = repo.save(updatingUser);
			logger.info(String.format("Updated user %s successfully.", savedUser.getId()));
			return new ObjectWrapper<Integer>(savedUser.getId(), "Updated user successfully.");
		}
	}

	@Override
	public User findByUsername(String username) {
		try 
		{
			Assert.assertNotEmpty(username);
			return repo.findByUsername(username);
		} 
		catch (ValidationException e) 
		{
			logger.error(e.getMessage());
		}
		return null;
	}

}
