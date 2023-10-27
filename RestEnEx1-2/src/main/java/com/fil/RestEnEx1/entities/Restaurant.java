package com.fil.RestEnEx1.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Restaurant {

	@Id
	private String restaurantId;
	private String restaurantName;
	private String restaurantArea;
	private String restaurantCity;
	private String restaurantPincode;
	private int restaurantTotalSeats;
	private int restaurantAvailableSeats;
	private int restaurantMealForTwo;
	private int resturantRating;

	
	@OneToMany(mappedBy = "restaurant")
	private List<MenuItem> restaurantMenu;

	@OneToOne(mappedBy = "restaurant")
	private Owner owner;

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantArea() {
		return restaurantArea;
	}

	public void setRestaurantArea(String restaurantArea) {
		this.restaurantArea = restaurantArea;
	}

	public String getRestaurantCity() {
		return restaurantCity;
	}

	public void setRestaurantCity(String restaurantCity) {
		this.restaurantCity = restaurantCity;
	}

	public String getRestaurantPincode() {
		return restaurantPincode;
	}

	public void setRestaurantPincode(String restaurantPincode) {
		this.restaurantPincode = restaurantPincode;
	}

	public int getRestaurantTotalSeats() {
		return restaurantTotalSeats;
	}

	public void setRestaurantTotalSeats(int restaurantTotalSeats) {
		this.restaurantTotalSeats = restaurantTotalSeats;
	}

	public int getRestaurantAvailableSeats() {
		return restaurantAvailableSeats;
	}

	public void setRestaurantAvailableSeats(int restaurantAvailableSeats) {
		this.restaurantAvailableSeats = restaurantAvailableSeats;
	}

	public int getRestaurantMealForTwo() {
		return restaurantMealForTwo;
	}

	public void setRestaurantMealForTwo(int restaurantMealForTwo) {
		this.restaurantMealForTwo = restaurantMealForTwo;
	}

	public int getResturantRating() {
		return resturantRating;
	}

	public void setResturantRating(int resturantRating) {
		this.resturantRating = resturantRating;
	}

	public List<MenuItem> getRestaurantMenu() {
		return restaurantMenu;
	}

	public void setRestaurantMenu(List<MenuItem> restaurantMenu) {
		this.restaurantMenu = restaurantMenu;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Restaurant(String restaurantId, String restaurantName, String restaurantArea, String restaurantCity,
			String restaurantPincode, int restaurantTotalSeats, int restaurantAvailableSeats, int restaurantMealForTwo,
			int resturantRating, List<MenuItem> restaurantMenu, Owner owner) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantArea = restaurantArea;
		this.restaurantCity = restaurantCity;
		this.restaurantPincode = restaurantPincode;
		this.restaurantTotalSeats = restaurantTotalSeats;
		this.restaurantAvailableSeats = restaurantAvailableSeats;
		this.restaurantMealForTwo = restaurantMealForTwo;
		this.resturantRating = resturantRating;
		this.restaurantMenu = restaurantMenu;
		this.owner = owner;
	}

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", restaurantArea="
				+ restaurantArea + ", restaurantCity=" + restaurantCity + ", restaurantPincode=" + restaurantPincode
				+ ", restaurantTotalSeats=" + restaurantTotalSeats + ", restaurantAvailableSeats="
				+ restaurantAvailableSeats + ", restaurantMealForTwo=" + restaurantMealForTwo + ", resturantRating="
				+ resturantRating + ", restaurantMenu=" + restaurantMenu + ", owner=" + owner + "]";
	}

}
