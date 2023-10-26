package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;


public class Restaurant {

	private String name;
	private String area;
	private String city;
	private String pincode;
	private int totalSeats;
	private int availableSeats;
	private int mealForTwo;
	private Menu menu;
	private int rating;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getMealForTwo() {
		return mealForTwo;
	}

	public void setMealForTwo(int mealForTwo) {
		this.mealForTwo = mealForTwo;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Restaurant(String name, String area, String city, String pincode, int totalSeats, int availableSeats,
			int mealForTwo, Menu menu, int rating) {
		super();
		this.name = name;
		this.area = area;
		this.city = city;
		this.pincode = pincode;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.mealForTwo = mealForTwo;
		this.menu = menu;
		this.rating = rating;
	}

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Resturant [name=" + name + ", area=" + area + ", city=" + city + ", pincode=" + pincode
				+ ", totalSeats=" + totalSeats + ", availableSeats=" + availableSeats + ", mealForTwo=" + mealForTwo
				+ ", menu=" + menu + ", rating=" + rating + "]";
	}

}
