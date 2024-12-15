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
import com.ecommerce.app.entity.State;
import com.ecommerce.app.service.CountryService;
import com.ecommerce.app.service.StateService;


@RestController
public class StatesController {
	
	@Autowired
	private StateService stateService;
	
	@GetMapping("/get-states-by-id")
	public ResponseEntity<HashMap<String, List<State>>> getStatesByCountryCode(int id){
		List<State> statesList = stateService.getStatesByCode(id);
		HashMap<String, List<State>> output = new HashMap<String, List<State>>();
		output.put("states", statesList);
		if(statesList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(output);
		}
	}

}
