package com.fil.RestEnEx1.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomMapEditor;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	private String category; // starter,maincourse,beverages,drinks
	private String itemName;
	private float itemPrice;
	
	@ManyToOne
    @JoinColumn(name="restaurantId", nullable=false)
	private Restaurant restaurant;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	

	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItem(int itemId, String category, String itemName, float itemPrice) {
	super();
	this.itemId = itemId;
	this.category = category;
	this.itemName = itemName;
	this.itemPrice = itemPrice;
}

	@Override
	public String toString() {
		return "MenuItem [itemId=" + itemId + ", category=" + category + ", itemName=" + itemName + ", itemPrice="
				+ itemPrice; // + ", restaurant=" + restaurant + "]";
	}

	

	
	
	
}
