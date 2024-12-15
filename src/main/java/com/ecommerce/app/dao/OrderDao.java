package com.ecommerce.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Address;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.entity.Order;


@Repository
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public final String getOrderHistoryQuery = "select * from orders o left outer join customer c on "
			+ "o.customer_id = c.customer_id where customer_id = ? ORDER by o.dat_created DESC";
	
	public List<Order> getOrderHistory(String email) throws Exception{
		List<Order> pastOrders = jdbcTemplate.query(getOrderHistoryQuery, new Object[] {email}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order o = new Order();
				o.setId(rs.getLong(1));
				o.setOrderTrackingNumber(rs.getString(2));
				o.setTotalPrice(rs.getBigDecimal(3));
				o.setTotalQuantity(rs.getInt(4));
				o.setBillingAddress(new Address());;
				o.getBillingAddress().setId(rs.getLong(5));
				o.setCustomer(new Customer());
				o.getCustomer().setId(rs.getLong(6));
				o.setShippingAddress(new Address());
				o.getShippingAddress().setId(rs.getLong(7));
				o.setStatus(rs.getString(8));
				o.setDateCreaded(rs.getDate(9));
				return o;
			}
			
		});
				return pastOrders;
	}
}
