package com.ecommerce.app.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.entity.ProductCategory;
import com.ecommerce.app.service.ProductCategoryService;


@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	
	@GetMapping("/get-product-categories")
	public ResponseEntity<HashMap<String, List<ProductCategory>>> getProductCategories(){
		List<ProductCategory> productList = productCategoryService.getProductCategories();
		HashMap<String, List<ProductCategory>> output = new HashMap<String, List<ProductCategory>>();
		output.put("productCategoryList", productList);
		if(productList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
}
