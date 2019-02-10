package com.phuongnguyen.onlineshopdata.repository;

import com.phuongnguyen.onlineshopdata.model.User;

public interface CustomUserRepository {
	User findByUsername(String username);
}
