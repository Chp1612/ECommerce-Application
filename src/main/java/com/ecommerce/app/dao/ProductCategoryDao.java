package com.ecommerce.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.ProductCategory;
import com.ecommerce.app.rowmapper.ProductCategoryRowMapper;
import com.ecommerce.app.rowmapper.ProductRowMapper;

@Repository
public class ProductCategoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String getProductCategories = "select * from product_category";
	
	public List<ProductCategory> getAllProductCategories(){
		List<ProductCategory> productCategoryList = jdbcTemplate.query(getProductCategories, new ProductCategoryRowMapper());
		return productCategoryList;
	}
	
	
}
