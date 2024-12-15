package com.ecommerce.app.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.service.ProductsService;

@RestController
public class ProductsController {
	
	@Autowired
	private ProductsService productsService;
	
	@GetMapping("/getProducts")
	public ResponseEntity<HashMap<String, List<Product>>> getAllProducts(){
		List<Product> productList = productsService.getAllProducts();
		HashMap<String, List<Product>> output = new HashMap<String, List<Product>>();
		output.put("productList", productList);
		if(productList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
	
	@GetMapping(value="/getProducts/product",params="categoryId")
	public ResponseEntity<HashMap<String, List<Product>>> getProductOnCategoryId(@RequestParam int categoryId){
		List<Product> productList = productsService.getProductById(categoryId);
		HashMap<String, List<Product>> output = new HashMap<String, List<Product>>();
		output.put("productList", productList);
		if(productList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
	
	@GetMapping(value="/getProducts/product",params="name")
	public ResponseEntity<HashMap<String, List<Product>>> getProductByName(@RequestParam String name){
		List<Product> productList = productsService.getProductByName(name);
		HashMap<String, List<Product>> output = new HashMap<String, List<Product>>();
		output.put("productList", productList);
		if(productList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
	
	@GetMapping(value="/getProducts/product",params="id")
	public ResponseEntity<HashMap<String, Product>> getProductDetailsById(@RequestParam int id){
		Product product = productsService.getProductDetailsById(id);
		HashMap<String, Product> output = new HashMap<String, Product>();
		output.put("product", product);
		if(product == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
	
	@GetMapping(value="/getProducts/product",params={"id","pageSize","start"})
	public ResponseEntity<HashMap<String, List<Product>>> getProductDetailsById(@RequestParam int id, 
							@RequestParam int pageSize, @RequestParam int start){
		System.out.println("4365");
		List<Product> productList = productsService.getProductByCategoryIdPaginator(id, pageSize, start);
		HashMap<String, List<Product>> output = new HashMap<String, List<Product>>();
		output.put("productList", productList);
		if(productList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
	
	@GetMapping(value="/getProducts/product",params={"name","pageSize","start"})
	public ResponseEntity<HashMap<String, List<Product>>> searchNameOfProductPaginated(@RequestParam String name, 
							@RequestParam int pageSize, @RequestParam int start){
		System.out.println("4365");
		List<Product> productList = productsService.searchNameOfProductsPaginated(name, pageSize, start);
		HashMap<String, List<Product>> output = new HashMap<String, List<Product>>();
		output.put("productList", productList);
		if(productList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
	
	
	
}
