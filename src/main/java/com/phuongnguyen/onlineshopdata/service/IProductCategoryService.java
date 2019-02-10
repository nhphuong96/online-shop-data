package com.phuongnguyen.onlineshopdata.service;

import java.util.List;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.model.ProductCategory;

public interface IProductCategoryService {
	ObjectWrapper<List<ProductCategory>> findAll();
	ObjectWrapper<Integer> insert(ProductCategory product);
	ObjectWrapper<Integer> deleteById(int id);
	ObjectWrapper<Integer> update(ProductCategory product);
}
