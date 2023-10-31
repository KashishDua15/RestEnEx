package com.fil.RestEnEx1.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="restenxOrders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
	private String restaurantName;
	private String tableNumber;
	private String numberOfPeople;
	private String bill;
	private String paymentStatus;
	private String restaurantRating;
	private List<String> itemsOrdered = new ArrayList<String>();
	@ManyToOne
    @JoinColumn(name="customerId", nullable=false)
    private Customer customer;
	
	@ManyToOne
    @JoinColumn(name="restaurantId", nullable=false)
    private Restaurant restaurant;
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getRestaurantRating() {
		return restaurantRating;
	}
	public void setRestaurantRating(String restaurantRating) {
		this.restaurantRating = restaurantRating;
	}
	public List<String> getItemsOrdered() {
		return itemsOrdered;
	}
	public void setItemsOrdered(List<String> itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Order(String restaurantName, String tableNumber, String numberOfPeople, String bill,
			String paymentStatus, String restaurantRating, List<String> itemsOrdered, Customer customer, Restaurant restaurant) {
		super();
		this.restaurantName = restaurantName;
		this.tableNumber = tableNumber;
		this.numberOfPeople = numberOfPeople;
		this.bill = bill;
		this.paymentStatus = paymentStatus;
		this.restaurantRating = restaurantRating;
		this.itemsOrdered = itemsOrdered;
		this.customer = customer;
		this.restaurant = restaurant;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", restaurantName=" + restaurantName + ", tableNumber=" + tableNumber
				+ ", numberOfPeople=" + numberOfPeople + ", bill=" + bill + ", paymentStatus=" + paymentStatus
				+ ", restaurantRating=" + restaurantRating + ", itemsOrdered=" + itemsOrdered + ", customer=" + customer
				+ ", restaurant=" + restaurant + "]";
	}
	

}
