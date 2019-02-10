package com.phuongnguyen.onlineshopdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phuongnguyen.onlineshopdata.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
