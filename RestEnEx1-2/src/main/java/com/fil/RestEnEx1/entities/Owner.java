package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Owner {
  
	@Id
	private int ownerId;
	private String name;
	private String contactno;
	private String emailId;
	private String password;
	
	@OneToOne
	@JoinColumn(name="restaurantId" , nullable=false)
	private Restaurant restaurant;
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Owner(int ownerId, String name, String contactno, String emailId, String password, Restaurant restaurant) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.contactno = contactno;
		this.emailId = emailId;
		this.password = password;
		this.restaurant = restaurant;
	}
	public Owner() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", name=" + name + ", contactno=" + contactno + ", emailId=" + emailId
				+ ", password=" + password + ", restaurant=" + restaurant + "]";
	}
	
	

}
