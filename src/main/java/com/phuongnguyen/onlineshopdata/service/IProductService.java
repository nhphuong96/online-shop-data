package com.phuongnguyen.onlineshopdata.service;

import java.util.List;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.model.Product;

public interface IProductService {
	ObjectWrapper<List<Product>> findAll();
	ObjectWrapper<Integer> insert(Product product);
	ObjectWrapper<Integer> deleteById(int id);
	ObjectWrapper<Integer> update(Product product);
}
