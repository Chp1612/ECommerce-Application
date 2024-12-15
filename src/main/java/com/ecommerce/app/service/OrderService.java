package com.ecommerce.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dao.OrderDao;
import com.ecommerce.app.entity.Order;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public List<Order> getOrders(String email) throws Exception{
		List<Order> orders = orderDao.getOrderHistory(email);
		Optional<List<Order>> nullCheck = Optional.of(orders);
		if(nullCheck.isPresent()) {
			return orders;
		}
		return new ArrayList<Order>();
	}
}
