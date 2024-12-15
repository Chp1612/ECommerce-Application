package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.ProductCategory;

public class ProductCategoryRowMapper implements RowMapper<ProductCategory>{

	@Override
	public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rs);
		ProductCategory p = new ProductCategory();
		p.setId(rs.getLong(1));
		p.setCategoryName(rs.getString(2));
		return p;
	}

}
