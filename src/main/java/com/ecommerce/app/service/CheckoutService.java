package com.ecommerce.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dao.CustomerDao;
import com.ecommerce.app.dto.PaymentInfoDto;
import com.ecommerce.app.dto.PurchaseDto;
import com.ecommerce.app.dto.PurchaseResponseDto;
import com.ecommerce.app.entity.Address;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.entity.Order;
import com.ecommerce.app.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;


@Service
public class CheckoutService {


	@Autowired
	private CustomerDao customerDao;
	
	@Value("{stripe.api.key}")
	private String apiKey;
	
	public CheckoutService() {
		Stripe.apiKey = apiKey;
	}


	@Transactional
	public PurchaseResponseDto placeOrder(PurchaseDto purchaseData) throws Exception{
		String orderTrackingNumber = generateOrderNumber();

		try {
			Order order = purchaseData.getOrder();

			order.setOrderTrackingNumber(orderTrackingNumber);

			Customer customer = purchaseData.getCustomer();
			
			Customer isExisting = customerDao.getCustomerByEmailId(customer);
			if(isExisting == null) {
				customer.setId(customerDao.addCustomer(customer));
			} else {
				customer.setId(isExisting.getId());
			}
			

			Address shippingAddress = purchaseData.getShippingAddress();
			shippingAddress.setId(customerDao.addAddress(shippingAddress));

			Address billingAddress = purchaseData.getBillingAddress();
			billingAddress.setId(customerDao.addAddress(billingAddress));

			order.setBillingAddress(billingAddress);
			order.setShippingAddress(shippingAddress);
			order.setCustomer(customer);
			order.setId(customerDao.addOrder(order));

			Set<OrderItem> items = purchaseData.getOrderItems();
			for (OrderItem i : items) {
				i.setOrder(order);
				i.setId(customerDao.addOrderItem(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		PurchaseResponseDto output = new PurchaseResponseDto();
		output.setOrderTrackingNumber(orderTrackingNumber);
		return output;

	}

	public PaymentIntent paymentIntent(PaymentInfoDto paymentInfo) throws StripeException{
		List<String> paymentMethods = new ArrayList<String>();
		paymentMethods.add("card");
		
		Map<String, Object>  params = new HashMap<String, Object>();
		params.put("amount", paymentInfo.getAmount());
		params.put("currency", paymentInfo.getCurrency());
		params.put("payment_method_types", paymentMethods);
		params.put("receipt_email", paymentInfo.getEmail());
		
		return PaymentIntent.create(params);
	}
	
	private String generateOrderNumber() {
		return UUID.randomUUID().toString();
	}
	

}
