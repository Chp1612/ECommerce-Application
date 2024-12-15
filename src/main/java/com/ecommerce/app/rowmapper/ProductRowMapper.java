package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rs);
		Product p = new Product();
		p.setId(rs.getLong(1));
		p.setSku(rs.getString(2));
		p.setName(rs.getString(3));
		p.setDescription(rs.getString(4));
		p.setUnitPrice(rs.getBigDecimal(5));
		p.setImageUrl(rs.getString(6));
		p.setActive(rs.getBoolean(7));
		p.setUnitsInStock(rs.getInt(8));
		p.setDateCreated(rs.getDate(9));
		p.setLastUpdated(rs.getDate(10));
		return p;
	}
	
	

}
