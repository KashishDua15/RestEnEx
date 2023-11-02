package com.fil.RestEnEx1.entities;

import java.util.List;

public class OwnerMainPageDetails {
	Owner owner;
	List<RestaurantOrders> resOrderHistory;
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public List<RestaurantOrders> getResOrderHistory() {
		return resOrderHistory;
	}
	public void setResOrderHistory(List<RestaurantOrders> resOrderHistory) {
		this.resOrderHistory = resOrderHistory;
	}
	public OwnerMainPageDetails(Owner owner, List<RestaurantOrders> resOrderHistory) {
		super();
		this.owner = owner;
		this.resOrderHistory = resOrderHistory;
	}
	public OwnerMainPageDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OwnerMainPageDetails [owner=" + owner + ", resOrderHistory=" + resOrderHistory + "]";
	}
	
}
