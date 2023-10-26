package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Restaurant {
     
	@Id
	private String restaurantId;
	private String restaurantname;
	private String restaurantarea;
	private String restaurantcity;
	private String restaurantpincode;
	private int restauranttotalSeats;
	private int restaurantavailableSeats;
	private int restaurantmealForTwo;
	private int rating;
	
	@OneToOne(mappedBy = "restaurant")
	private Owner owner;
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantname() {
		return restaurantname;
	}
	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}
	public String getRestaurantarea() {
		return restaurantarea;
	}
	public void setRestaurantarea(String restaurantarea) {
		this.restaurantarea = restaurantarea;
	}
	public String getRestaurantcity() {
		return restaurantcity;
	}
	public void setRestaurantcity(String restaurantcity) {
		this.restaurantcity = restaurantcity;
	}
	public String getRestaurantpincode() {
		return restaurantpincode;
	}
	public void setRestaurantpincode(String restaurantpincode) {
		this.restaurantpincode = restaurantpincode;
	}
	public int getRestauranttotalSeats() {
		return restauranttotalSeats;
	}
	public void setRestauranttotalSeats(int restauranttotalSeats) {
		this.restauranttotalSeats = restauranttotalSeats;
	}
	public int getRestaurantavailableSeats() {
		return restaurantavailableSeats;
	}
	public void setRestaurantavailableSeats(int restaurantavailableSeats) {
		this.restaurantavailableSeats = restaurantavailableSeats;
	}
	public int getRestaurantmealForTwo() {
		return restaurantmealForTwo;
	}
	public void setRestaurantmealForTwo(int restaurantmealForTwo) {
		this.restaurantmealForTwo = restaurantmealForTwo;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Restaurant(String restaurantId, String restaurantname, String restaurantarea, String restaurantcity,
			String restaurantpincode, int restauranttotalSeats, int restaurantavailableSeats, int restaurantmealForTwo,
			int rating, Owner owner) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantname = restaurantname;
		this.restaurantarea = restaurantarea;
		this.restaurantcity = restaurantcity;
		this.restaurantpincode = restaurantpincode;
		this.restauranttotalSeats = restauranttotalSeats;
		this.restaurantavailableSeats = restaurantavailableSeats;
		this.restaurantmealForTwo = restaurantmealForTwo;
		this.rating = rating;
		this.owner = owner;
	}
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantname=" + restaurantname + ", restaurantarea="
				+ restaurantarea + ", restaurantcity=" + restaurantcity + ", restaurantpincode=" + restaurantpincode
				+ ", restauranttotalSeats=" + restauranttotalSeats + ", restaurantavailableSeats="
				+ restaurantavailableSeats + ", restaurantmealForTwo=" + restaurantmealForTwo + ", rating=" + rating
				+ ", owner=" + owner + "]";
	}

	
}
