package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;

import com.fil.RestEnEx1.entities.Menu;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;

public interface OwnerService {
	
	public Owner ownerSignIn(String email, String password);
	public Restaurant addRestaurant(Restaurant restaurant);
	
	public int updateAvailableSeats(String restaurantId);
	
	public int updateNoofSeats(String restaurantId);
	
	public Menu addMenu(Menu menu);
	
	public int viewRating(String restaurantId);
	
	public Optional<Menu> viewOrder (int orderId);
}
