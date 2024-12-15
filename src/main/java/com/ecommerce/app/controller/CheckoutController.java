package com.ecommerce.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.dto.PaymentInfoDto;
import com.ecommerce.app.dto.PurchaseDto;
import com.ecommerce.app.dto.PurchaseResponseDto;
import com.ecommerce.app.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping("/purchase")
	public ResponseEntity<Map<String, PurchaseResponseDto>> placeOrder(@RequestBody PurchaseDto purchase){
		PurchaseResponseDto purchaseOutput = new PurchaseResponseDto();
		Map<String, PurchaseResponseDto> output = new HashMap<String, PurchaseResponseDto>();
		try {
			purchaseOutput = checkoutService.placeOrder(purchase);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		output.put("purchaseId", purchaseOutput);
		return ResponseEntity.status(HttpStatus.OK).body(output);
		
	}

	@PostMapping("/payment-intent")
	public ResponseEntity<String> paymentIntent(@RequestBody PaymentInfoDto paymentInfo) throws StripeException{
		
		PaymentIntent intent = checkoutService.paymentIntent(paymentInfo);
		String intentStr = intent.toJson();
		
		return new ResponseEntity<>(intentStr, HttpStatus.OK);
	}
}
