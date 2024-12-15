package com.ecommerce.app.dto;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ecommerce.app.entity.Address;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.entity.Order;
import com.ecommerce.app.entity.OrderItem;

@Component
public class PurchaseResponseDto {
	
	private String orderTrackingNumber;

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}
	
	
	
	
}
