package com.ecommerce.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dao.ProductsDao;
import com.ecommerce.app.entity.Product;

@Service
public class ProductsService {
	
	@Autowired
	private ProductsDao productsDao;

	public List<Product> getAllProducts() {
		List<Product> products = productsDao.getAllProducts().stream().filter(p -> p.isActive()).collect(Collectors.toList());
		return products;
		
	}

	public List<Product> getProductById(int id) {
		List<Product> productListById = productsDao.getProductById(id).stream()
				.filter(p -> p.isActive()).collect(Collectors.toList());
		Optional<List<Product>> checkNullProducts = Optional.ofNullable(productListById);
		if(checkNullProducts.isPresent()) {
			return productListById;
		} else {
			return new ArrayList<Product>();
		}
	}
	
	public List<Product> getProductByName(String name) {
		List<Product> productListByName = productsDao.getProductByName(name).stream()
				.filter(p -> p.isActive()).collect(Collectors.toList());
		Optional<List<Product>> checkNullProducts = Optional.ofNullable(productListByName);
		if(checkNullProducts.isPresent()) {
			return productListByName;
		} else {
			return new ArrayList<Product>();
		}
	}

	public Product getProductDetailsById(int id) {
		Product product = productsDao.getProductDetail(id);
		return product;
	}
	
	public List<Product> getProductByCategoryIdPaginator(int id, int pageSize, int start) {
		List<Product> productList = productsDao.getProductByCategoryPaginator(id, pageSize, start).stream()
				.filter(p -> p.isActive()).collect(Collectors.toList());
		Optional<List<Product>> checkNullProducts = Optional.ofNullable(productList);
		if(checkNullProducts.isPresent()) {
			return productList;
		} else {
			return new ArrayList<Product>();
		}
	}
	
	public List<Product> searchNameOfProductsPaginated(String name, int pageSize, int start) {
		List<Product> productListByName = productsDao.searchProductByNamePaginator(name, pageSize, start).stream()
				.filter(p -> p.isActive()).collect(Collectors.toList());
		Optional<List<Product>> checkNullProducts = Optional.ofNullable(productListByName);
		if(checkNullProducts.isPresent()) {
			return productListByName;
		} else {
			return new ArrayList<Product>();
		}
	}

}
