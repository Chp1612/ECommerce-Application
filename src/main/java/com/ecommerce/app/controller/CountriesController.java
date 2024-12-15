package com.ecommerce.app.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.entity.Country;
import com.ecommerce.app.service.CountryService;


@RestController
public class CountriesController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/get-countries")
	public ResponseEntity<HashMap<String, List<Country>>> getAllCountries(){
		List<Country> countrieList = countryService.getAllCountries();
		HashMap<String, List<Country>> output = new HashMap<String, List<Country>>();
		output.put("countries", countrieList);
		if(countrieList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}
	

}
