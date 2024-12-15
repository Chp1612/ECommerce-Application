package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.Country;

public class CountryRowMapper implements RowMapper<Country>{

	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		Country c = new Country();
		c.setId(rs.getInt(1));
		c.setCode(rs.getString(2));
		c.setName(rs.getString(3));
		return c;
	}

}
