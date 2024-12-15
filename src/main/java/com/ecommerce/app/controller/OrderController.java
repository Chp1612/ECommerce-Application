package com.ecommerce.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.entity.Order;
import com.ecommerce.app.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order-history")
	public ResponseEntity<Map<String, List<Order>>> getPastordersOfCustomer(@RequestParam() String email){
		List<Order> ordersList = null;
		Map<String, List<Order>> orders = new HashMap<String, List<Order>>();
		try{
			ordersList = orderService.getOrders(email);
			orders.put("pastOrders", ordersList);
			return ResponseEntity.status(HttpStatus.OK).body(orders);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}
}
