package com.fil.RestEnEx1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


public class Owner {
	
	private String name;
	
	private String contactno;
	
	private int ownerId;
	
	private String emailId;
	
	private Restaurant restaurant;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Owner(String name, String contactno, int ownerId, String emailId, Restaurant restaurant, String password) {
		super();
		this.name = name;
		this.contactno = contactno;
		this.ownerId = ownerId;
		this.emailId = emailId;
		this.restaurant = restaurant;
		this.password = password;
	}

	public Owner() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Owner [name=" + name + ", contactno=" + contactno + ", ownerId=" + ownerId + ", emailId=" + emailId
				+ ", restaurant=" + restaurant + ", password=" + password + "]";
	}
	

}
