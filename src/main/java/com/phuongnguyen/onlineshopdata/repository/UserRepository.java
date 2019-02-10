package com.phuongnguyen.onlineshopdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phuongnguyen.onlineshopdata.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, CustomUserRepository {
}
