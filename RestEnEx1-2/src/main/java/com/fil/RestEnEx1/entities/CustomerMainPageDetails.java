package com.fil.RestEnEx1.entities;

import java.util.List;

public class CustomerMainPageDetails {
	Customer customer;
	List<CustomerOrders> orderHistory;
	List<Restaurant> restaurants;
	Restaurant restaurant;
	Menu menu;
	
	
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<CustomerOrders> getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(List<CustomerOrders> orderHistory) {
		this.orderHistory = orderHistory;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
	public CustomerMainPageDetails(Customer customer, List<CustomerOrders> orderHistory, List<Restaurant> restaurants,
			Restaurant restaurant, Menu menu) {
		super();
		this.customer = customer;
		this.orderHistory = orderHistory;
		this.restaurants = restaurants;
		this.restaurant = restaurant;
		this.menu = menu;
	}
	public CustomerMainPageDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerMainPageDetails [customer=" + customer + ", orderHistory=" + orderHistory + ", restaurants="
				+ restaurants + "]";
	}

}