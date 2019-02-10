package com.phuongnguyen.onlineshopdata.service;

import java.util.List;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.model.User;

public interface IUserService {
	ObjectWrapper<List<User>> findAll();
	User findByUsername(String username);
	ObjectWrapper<Integer> insert(User product);
	ObjectWrapper<Integer> deleteById(int id);
	ObjectWrapper<Integer> update(User product);
}
