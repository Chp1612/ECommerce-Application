package com.ecommerce.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
public class Customer {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Order> orders = new HashSet<Order>();
	
	public void add(Order o) {
		if(o != null) {
			if(orders == null) {
				orders = new HashSet<Order>();
			}
			orders.add(o);
			o.setCustomer(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
