package com.ecommerce.app.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.app.entity.Address;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.entity.Order;
import com.ecommerce.app.entity.OrderItem;

@Repository
@Transactional
public class CustomerDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	public final String ADD_CUSTOMER = "insert into customer(first_name, last_name, email) values(:firstName, :lastName, :email)";
	
	public final String ADD_ADDRESS = "insert into address(city, country, state, street, zip_code) values(:city, :country, :state, :street, :zipCode)";
	
	public final String ADD_ORDERS = "insert into orders(order_tracking_number, total_price, total_quantity, "
			+ "billing_address_id, customer_id, shipping_address_id, status, date_created, last_updated)"
			+ " values(:trackingNo, :price, :quantity, :billingId, :customerId, :shippingId, :status, :dateCreated, :updated)";
	
	public final String ADD_ORDERITEM = "insert into order_item(image_url, quantity, unit_price, order_id, product_id)"
			+ "values(:imageUrl, :quantity,:unitPrice, :orderId, :productId)";
	
	public final String getCustomerByEmail = "select * from customer where customer.email = :email";

	KeyHolder keyHolder = new GeneratedKeyHolder();
	
	public Long addCustomer(Customer c) throws Exception{
		MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", c.getFirstName())
                .addValue("lastName", c.getLastName())
                .addValue("email", c.getEmail());
		 jdbcTemplate.update(ADD_CUSTOMER, parameters, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	public Long addAddress(Address a) throws Exception {
		MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("city", a.getCity())
                .addValue("country", a.getCountry())
                .addValue("state", a.getState())
                .addValue("street", a.getStreet())
                .addValue("zipCode", a.getZipCode());
		 jdbcTemplate.update(ADD_ADDRESS, parameters, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	public Long addOrder(Order o) throws Exception {
		LocalDateTime yourLocalDateTime = LocalDateTime.now() ;
		
		MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("trackingNo", o.getOrderTrackingNumber())
                .addValue("price", o.getTotalPrice())
                .addValue("quantity", o.getTotalQuantity())
                .addValue("billingId", o.getBillingAddress().getId())
                .addValue("customerId", o.getCustomer().getId())
                .addValue("shippingId", o.getShippingAddress().getId())
                .addValue("status", o.getStatus())
                .addValue("dateCreated", yourLocalDateTime)
                .addValue("updated", o.getLastUpdated());
		 jdbcTemplate.update(ADD_ORDERS, parameters, keyHolder);
		return keyHolder.getKey().longValue();
	}
	//insert into order_item(image_url, quantity, unit_price, order_id, product_id)
	public Long addOrderItem(OrderItem item) throws Exception {
		MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("imageUrl", item.getImageUrl())
                .addValue("quantity", item.getQuantity())
                .addValue("unitPrice", item.getUnitPrice())
                .addValue("orderId", item.getOrder().getId())
                .addValue("productId", item.getProductId());
		 jdbcTemplate.update(ADD_ORDERITEM, parameters, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	public Customer getCustomerByEmailId(Customer c) throws Exception{
		MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("email", c.getEmail());
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", c.getEmail());
		 Customer cust = (Customer) jdbcTemplate.query(ADD_CUSTOMER, paramMap, new RowMapper() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer c1 = new Customer();
				c1.setId(rs.getLong(1));
				c1.setFirstName(rs.getString(2));
				return c1;
			}
			 
		});
		return cust;
	}
	
}