package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Owner {
  
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long ownerId; 
	private String name;
	private String contactno;
	private String emailId;
	private String password;
	
	@OneToOne
	@JoinColumn(name="restaurantId" )
	private Restaurant restaurant;

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
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

	public Owner(long ownerId, String name, String contactno, String emailId, String password) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.contactno = contactno;
		this.emailId = emailId;
		this.password = password;
	}

	public Owner() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", name=" + name + ", contactno=" + contactno + ", emailId=" + emailId
				+ ", password=" + password+ "]";
	}
	
}
