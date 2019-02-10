package com.phuongnguyen.onlineshopdata.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.model.Product;
import com.phuongnguyen.onlineshopdata.repository.ProductRepository;
import com.phuongnguyen.onlineshopdata.service.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	final static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository repo;
	
	@Override
	public ObjectWrapper<List<Product>> findAll() {
		List<Product> productList = repo.findAll();
		if (productList != null)
		{
			return new ObjectWrapper<List<Product>>(productList);
		}
		return new ObjectWrapper<List<Product>>(null, false);
			
	}

	@Override
	public ObjectWrapper<Integer> insert(Product product) {
		if (!repo.existsById(product.getId()))
		{
			Product persistedProduct = repo.save(product);
			logger.info(String.format("Persisted product %s successfully with Product ID: %s", persistedProduct.getName(), persistedProduct.getId()));
			return new ObjectWrapper<Integer>(product.getId(), "Created new product successfully.");
		}
		else
		{
			logger.error(String.format("Product ID %s already existed in database.", product.getId()));
			return new ObjectWrapper<Integer>(0, false, "Product has already existed.");
		}
	}

	@Override
	public ObjectWrapper<Integer> deleteById(int id) {
		try
		{
			repo.deleteById(id);
			return new ObjectWrapper<Integer>(id, "Delete product successfully.");
		}
		catch (Exception e)
		{
			logger.error("Error occurred while deleting product id = " + id);
		}
		return new ObjectWrapper<Integer>(0, false);
	}

	@Override
	public ObjectWrapper<Integer> update(Product updatingProduct) {
		if (updatingProduct.getId() == 0)
		{
			logger.error("Invalid product Id");
			return new ObjectWrapper<Integer>(0, false, "Invalid product Id: " + updatingProduct.getId());
		}
		else
		{
			Product savedProduct = repo.save(updatingProduct);
			logger.info(String.format("Updated product %s successfully.", savedProduct.getId()));
			return new ObjectWrapper<Integer>(savedProduct.getId(), "Updated product successfully.");
		}
	}

}
