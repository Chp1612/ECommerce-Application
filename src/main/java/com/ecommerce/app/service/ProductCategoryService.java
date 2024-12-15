package com.ecommerce.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dao.ProductCategoryDao;
import com.ecommerce.app.entity.ProductCategory;

@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryDao productCategoryDao;

	public List<ProductCategory> getProductCategories() {
			List<ProductCategory> productCategories = productCategoryDao.getAllProductCategories();
			return productCategories;
			

	}

}
