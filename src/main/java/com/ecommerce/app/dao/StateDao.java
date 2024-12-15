package com.ecommerce.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Country;
import com.ecommerce.app.entity.State;
import com.ecommerce.app.rowmapper.StateRowMapper;

@Repository
public class StateDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final static String getStatesByCountryCode = "select * from state where country_id = ?";
			
	public List<State> getStatesByCode(int id){
		List<State> listOfStates = jdbcTemplate.query(getStatesByCountryCode, new Object[] {id}, new StateRowMapper());
		return listOfStates;
	}

}
