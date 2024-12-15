package com.ecommerce.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.rowmapper.ProductRowMapper;

@Repository
public class ProductsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String getAllProductsQuery = "select * from product";
	
	private final String getProductsOnCategoryId = "select * from product where category_id=?";
	
	private final String getProdcustOnName = "select * from product where name LIKE CONCAT('%',?,'%')";
	
	private final String getProductDetailsById = "select * from product where id=?";
	
	private final String getProductsPaginated = "select * from product where category_id=? LIMIT ? OFFSET ?";
	
	private final String searchNameOfProductsPaginated = "select * from product where name LIKE CONCAT('%',?,'%') LIMIT ? OFFSET ?";
	
	public List<Product> getAllProducts(){
		List<Product> productsList = jdbcTemplate.query(getAllProductsQuery, new ProductRowMapper());
		return productsList;
	}

	public List<Product> getProductById(int id) {
		List<Product> productsList = jdbcTemplate.query(getProductsOnCategoryId, new Object[] { id }, new ProductRowMapper());
		System.out.println(productsList);
		return productsList;
	}
	
	public List<Product>getProductByName(String name){
		List<Product> producstList = jdbcTemplate.query(getProdcustOnName, new Object[] {name}, new ProductRowMapper());
		return producstList;
	}

	public Product getProductDetail(int id) {
		Product p = jdbcTemplate.queryForObject(getProductDetailsById, new Object[] { id }, new ProductRowMapper());
		return p;
	}
	
	public List<Product> getProductByCategoryPaginator(int id, int pageSize, int start) {
		List<Product> productsList = jdbcTemplate.query(getProductsPaginated, new Object[] { id, 
				pageSize, start}, new ProductRowMapper());
		System.out.println(productsList);
		return productsList;
	}
	
	public List<Product> searchProductByNamePaginator(String name, int pageSize, int start) {
		List<Product> productsList = jdbcTemplate.query(searchNameOfProductsPaginated, new Object[] { name, 
				pageSize, start}, new ProductRowMapper());
		System.out.println(productsList);
		return productsList;
	}
	
	

}
