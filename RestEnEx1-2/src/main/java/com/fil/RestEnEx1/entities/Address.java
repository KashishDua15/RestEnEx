package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
	private String houseNumber;
	private String street;
	private String area;
	private String city;
	private long pincode;
	@OneToOne
    @JoinColumn(name="customerId", nullable=false)
    private Customer customer;
	
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Address(String houseNumber, String street, String area, String city, long pincode, Customer customer) {
		super();
		this.houseNumber = houseNumber;
		this.street = street;
		this.area = area;
		this.city = city;
		this.pincode = pincode;
		this.customer = customer;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", street=" + street + ", area=" + area + ", city=" + city
				+ ", pincode=" + pincode + ", customer=" + customer + "]";
	}
	
}
