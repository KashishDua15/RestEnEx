package com.fil.RestEnEx1.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fil.RestEnEx1.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, UUID>{
	public Customer findByCustomerEmail(String customerEmail);

}
