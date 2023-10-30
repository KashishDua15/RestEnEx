package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;

import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;

public interface OwnerService {
	
	public void ownerSignUp(Owner owner);
	public Owner ownerSignIn(String email, String password);
	public Restaurant addRestaurant(Restaurant restaurant);
	public int updateAvailableSeats(long restaurantId,int availableNoOfSeats);
	public int updateTotalSeats(long restaurantId,int updateTotalSeats);
	public List<MenuItem> addMenu(long restaurantId,List<MenuItem> menuItem);
	public int getRating(long restaurantId);
	public Order getOrder (long orderId);
	public List<Order> getAllOrders();
	
}
