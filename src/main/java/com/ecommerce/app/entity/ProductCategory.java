package com.ecommerce.app.entity;

import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class ProductCategory {
	
	private Long id;

    private String categoryName;
    
    private Set<Product> products;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}


}







