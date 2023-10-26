package com.fil.RestEnEx1.entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Embeddable
public class CustomerProfile {

	private Set<String> customerFavourites = new LinkedHashSet<String>();
	 @ElementCollection
	private List<CustomerOrders> orderHistory = new ArrayList<CustomerOrders>();

	public Set<String> getCustomerFavourites() {
		return customerFavourites;
	}
	public void setCustomerFavourites(Set<String> customerFavourites) {
		this.customerFavourites = customerFavourites;
	}
	public List<CustomerOrders> getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(List<CustomerOrders> orderHistory) {
		this.orderHistory = orderHistory;
	}
	public CustomerProfile( Set<String> customerFavourites,
			List<CustomerOrders> orderHistory) {
		super();
		this.customerFavourites = customerFavourites;
		this.orderHistory = orderHistory;
	}
	public CustomerProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerProfile customerFavourites="
				+ customerFavourites + ", orderHistory=" + orderHistory + "]";
	}
	
	
	

}
