package com.fil.RestEnEx1.entities;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID customerId; 
	private String customerName;
	private String customerEmail;
	private String customerContactNumber; 
	private String customerPassword; 
	@Embedded
	private Address customerAddress;
	private Set<String> customerFavourites = new LinkedHashSet<String>();
	@OneToMany(mappedBy="customer")
	private List<Order> orderHistory = new ArrayList<Order>();

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Set<String> getCustomerFavourites() {
		return customerFavourites;
	}

	public void setCustomerFavourites(Set<String> customerFavourites) {
		this.customerFavourites = customerFavourites;
	}

	public List<Order> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(List<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}

	public Customer(String customerName, String customerEmail, String customerContactNumber,
			String customerPassword, Address customerAddress, Set<String> customerFavourites,
			List<Order> orderHistory) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContactNumber = customerContactNumber;
		this.customerPassword = customerPassword;
		this.customerAddress = customerAddress;
		this.customerFavourites = customerFavourites;
		this.orderHistory = orderHistory;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerContactNumber=" + customerContactNumber + ", customerPassword="
				+ customerPassword + ", customerAddress=" + customerAddress + ", customerFavourites="
				+ customerFavourites + ", orderHistory=" + orderHistory + "]";
	}
	
	
	
	
}
