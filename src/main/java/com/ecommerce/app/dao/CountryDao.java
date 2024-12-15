package com.ecommerce.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Country;
import com.ecommerce.app.rowmapper.CountryRowMapper;
import com.ecommerce.app.rowmapper.StateRowMapper;

@Repository
public class CountryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final static String getAllCountries = "select * from country";
			
	public List<Country> getAllCountries(){
		List<Country> listOfStates = jdbcTemplate.query(getAllCountries, new CountryRowMapper());
		return listOfStates;
	}
}
