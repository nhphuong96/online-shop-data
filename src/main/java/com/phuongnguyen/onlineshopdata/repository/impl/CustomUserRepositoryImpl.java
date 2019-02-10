package com.phuongnguyen.onlineshopdata.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.phuongnguyen.onlineshopdata.model.User;
import com.phuongnguyen.onlineshopdata.repository.CustomUserRepository;

@Repository
@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findByUsername(String username) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username LIKE :username", User.class);
		query.setParameter("username", username);
		List<User> resultList = query.getResultList();
		if (!CollectionUtils.isEmpty(resultList))
		{
			return resultList.get(0);
		}
		return null;
	}

}
