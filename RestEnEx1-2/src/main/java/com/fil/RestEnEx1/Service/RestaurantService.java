package com.fil.RestEnEx1.Service;

import java.util.List;

import com.fil.RestEnEx1.entities.Menu;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Restaurant;

public interface RestaurantService {
	public Restaurant addRestaurant(Restaurant restaurant);
	
	public int updateAvailableSeats(String restaurantId);
	
	public int updateNoofSeats(String restaurantId);
	
	public Menu addMenu(Menu menu);
	
	public int viewRating(String restaurantId);
	
	public List<Order> viewOrder (int orderId);
}
