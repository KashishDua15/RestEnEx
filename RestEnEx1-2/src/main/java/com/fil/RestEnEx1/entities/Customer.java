package com.fil.RestEnEx1.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Customer {
	@Id
	private String customerId; 
	private String customerName;
	private String customerEmail;
	private String customerContactNumber; 
	private String customerPassword; 
	private Address customerAddress;
	private CustomerProfile customerProfile;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
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
	public CustomerProfile getCustomerProfile() {
		return customerProfile;
	}
	public void setCustomerProfile(CustomerProfile customerProfile) {
		this.customerProfile = customerProfile;
	}
	public Customer(String customerId, String customerName, String customerEmail, String customerContactNumber,
			String customerPassword, Address customerAddress, CustomerProfile customerProfile) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContactNumber = customerContactNumber;
		this.customerPassword = customerPassword;
		this.customerAddress = customerAddress;
		this.customerProfile = customerProfile;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerContactNumber=" + customerContactNumber + ", customerPassword="
				+ customerPassword + ", customerAddress=" + customerAddress + ", customerProfile=" + customerProfile
				+ "]";
	}
	

}
