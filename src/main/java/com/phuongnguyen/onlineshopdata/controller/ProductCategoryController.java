package com.phuongnguyen.onlineshopdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phuongnguyen.onlineshopdata.core.ObjectWrapper;
import com.phuongnguyen.onlineshopdata.model.ProductCategory;
import com.phuongnguyen.onlineshopdata.service.IProductCategoryService;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

	@Autowired
	private IProductCategoryService service;
	
	@GetMapping(value = "/findAll")
	public ObjectWrapper<List<ProductCategory>> findAll() {
		return service.findAll();
	}
	
	@PostMapping(value = "/save")
	public ObjectWrapper<Integer> save(@RequestBody ProductCategory product)
	{
		return service.insert(product);
	}
	
	@PostMapping(value = "/deleteById")
	public ObjectWrapper<Integer> delete(@RequestParam(name = "id", required = true) int id)
	{
		return service.deleteById(id);
	}
	
	@PostMapping(value = "/update")
	public ObjectWrapper<Integer> update(@RequestBody ProductCategory product)
	{
		return service.update(product);
	}
	
}
