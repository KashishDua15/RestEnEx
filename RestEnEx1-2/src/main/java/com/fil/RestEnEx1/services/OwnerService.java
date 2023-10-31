package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;

public interface OwnerService {
	
	public void ownerSignUp(Owner owner);
	public Owner ownerSignIn(String email, String password);
	public Restaurant addRestaurant(Restaurant restaurant);
	public Restaurant updateAvailableSeats(UUID restaurantId,int availableNoOfSeats);
	public Restaurant updateTotalSeats(UUID restaurantId,int updateTotalSeats);
	public void addMenuItem(UUID restaurantId,MenuItem menuItem);
	public int getRating(UUID restaurantId);
	public Order getOrder (UUID orderId);
	public List<Order> getAllOrders();
	
}
