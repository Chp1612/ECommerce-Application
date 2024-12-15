package com.ecommerce.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dao.StateDao;
import com.ecommerce.app.entity.Country;
import com.ecommerce.app.entity.State;

@Service
public class StateService {

	@Autowired
	private StateDao stateDao;
	
	public List<State> getStatesByCode(int id){
		List<State> statesByCountryCode = stateDao.getStatesByCode(id);
		Optional<List<State>> nullCheck = Optional.of(statesByCountryCode);
		if(nullCheck.isPresent()) {
			return statesByCountryCode;
		}
		return new ArrayList<State>();
	}
	
}
