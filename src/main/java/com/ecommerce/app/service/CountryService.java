package com.ecommerce.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dao.CountryDao;
import com.ecommerce.app.entity.Country;

@Service
public class CountryService {

	@Autowired
	private CountryDao countryDao;
	
	public List<Country> getAllCountries(){
		List<Country> countriesList = countryDao.getAllCountries();
		Optional<List<Country>> nullCheck = Optional.of(countriesList);
		if(nullCheck.isPresent()) {
			return countriesList;
		}
		return new ArrayList<Country>();
	}
}
