package com.fil.RestEnEx1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fil.RestEnEx1.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>{
	public Customer findByCustomerEmail(String customerEmail);

}
