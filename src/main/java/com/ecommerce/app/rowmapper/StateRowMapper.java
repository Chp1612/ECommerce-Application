package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.State;

public class StateRowMapper implements RowMapper<State>{

	@Override
	public State mapRow(ResultSet rs, int rowNum) throws SQLException {
		State s = new State();
		s.setId(rs.getInt(1));
		s.setName(rs.getString(2));
		return s;
	}


}
