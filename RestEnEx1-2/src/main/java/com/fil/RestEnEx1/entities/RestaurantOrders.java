package com.fil.RestEnEx1.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class RestaurantOrders {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
	private String restaurantName;
	private String tableNumber;
	private String numberOfPeople;
	private String bill;
	private String paymentStatus;
	private String restaurantRating;
	private String dateBookedFor;
	private String timeBookedFor;
	private List<String> itemsOrdered = new ArrayList<String>();
	private Date dateOrdered;
	private UUID customerId;
	
	public String getDateBookedFor() {
		return dateBookedFor;
	}

	public void setDateBookedFor(String dateBookedFor) {
		this.dateBookedFor = dateBookedFor;
	}

	public String getTimeBookedFor() {
		return timeBookedFor;
	}

	public void setTimeBookedFor(String timeBookedFor) {
		this.timeBookedFor = timeBookedFor;
	}

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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}



	public RestaurantOrders(String restaurantName, String tableNumber, String numberOfPeople, String bill,
			String paymentStatus, String restaurantRating, String dateBookedFor, String timeBookedFor,
			List<String> itemsOrdered, Date dateOrdered, UUID customerId, Restaurant restaurant) {
		super();
		this.restaurantName = restaurantName;
		this.tableNumber = tableNumber;
		this.numberOfPeople = numberOfPeople;
		this.bill = bill;
		this.paymentStatus = paymentStatus;
		this.restaurantRating = restaurantRating;
		this.dateBookedFor = dateBookedFor;
		this.timeBookedFor = timeBookedFor;
		this.itemsOrdered = itemsOrdered;
		this.dateOrdered = dateOrdered;
		this.customerId = customerId;
		this.restaurant = restaurant;
	}

	public RestaurantOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RestaurantOrders [orderId=" + orderId + ", restaurantName=" + restaurantName + ", tableNumber="
				+ tableNumber + ", numberOfPeople=" + numberOfPeople + ", bill=" + bill + ", paymentStatus="
				+ paymentStatus + ", restaurantRating=" + restaurantRating + ", itemsOrdered=" + itemsOrdered
				+ ", restaurant=" + restaurant + "]";
	}
	
	

}
