package com.phuongnguyen.onlineshopdata.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.model.ProductCategory;
import com.phuongnguyen.onlineshopdata.repository.ProductCategoryRepository;
import com.phuongnguyen.onlineshopdata.service.IProductCategoryService;

@Service
@Transactional
public class ProductCategoryServiceImpl implements IProductCategoryService {

	final static Logger logger = Logger.getLogger(ProductCategoryServiceImpl.class);
	
	@Autowired
	private ProductCategoryRepository repo;
	
	@Override
	public ObjectWrapper<List<ProductCategory>> findAll() 
	{
		List<ProductCategory> ProductCategoryList = repo.findAll();
		if (ProductCategoryList != null)
		{
			return new ObjectWrapper<List<ProductCategory>>(ProductCategoryList);
		}
		return new ObjectWrapper<List<ProductCategory>>(null, false);
			
	}

	@Override
	public ObjectWrapper<Integer> insert(ProductCategory ProductCategory) 
	{
		if (!repo.existsById(ProductCategory.getId()))
		{
			ProductCategory persistedProductCategory = repo.save(ProductCategory);
			logger.info(String.format("Persisted ProductCategory %s successfully with ProductCategory ID: %s", persistedProductCategory.getName(), persistedProductCategory.getId()));
			return new ObjectWrapper<Integer>(ProductCategory.getId(), "Created new ProductCategory successfully.");
		}
		else
		{
			logger.error(String.format("ProductCategory ID %s already existed in database.", ProductCategory.getId()));
			return new ObjectWrapper<Integer>(0, false, "ProductCategory has already existed.");
		}
	}

	@Override
	public ObjectWrapper<Integer> deleteById(int id) {
		try
		{
			repo.deleteById(id);
			return new ObjectWrapper<Integer>(id, "Delete ProductCategory successfully.");
		}
		catch (Exception e)
		{
			logger.error("Error occurred while deleting ProductCategory id = " + id);
		}
		return new ObjectWrapper<Integer>(0, false);
	}

	@Override
	public ObjectWrapper<Integer> update(ProductCategory updatingProductCategory) {
		if (updatingProductCategory.getId() == 0)
		{
			logger.error("Invalid ProductCategory Id");
			return new ObjectWrapper<Integer>(0, false, "Invalid ProductCategory Id: " + updatingProductCategory.getId());
		}
		else
		{
			ProductCategory savedProductCategory = repo.save(updatingProductCategory);
			logger.info(String.format("Updated ProductCategory %s successfully.", savedProductCategory.getId()));
			return new ObjectWrapper<Integer>(savedProductCategory.getId(), "Updated ProductCategory successfully.");
		}
	}

}
