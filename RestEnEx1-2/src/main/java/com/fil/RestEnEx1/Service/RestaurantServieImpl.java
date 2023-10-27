package com.fil.RestEnEx1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fil.RestEnEx1.Repository.MenuItemRepository;
import com.fil.RestEnEx1.entities.Menu;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Restaurant;

public class RestaurantServieImpl {
	
	@Autowired 
	private MenuItemRepository menuItemrepository;
	public Restaurant addRestaurant(Restaurant restaurant) {
		menuItemrepository.save(restaurant);
		return restaurant;
		
		
	}
	
	public int updateAvailableSeats(String restaurantId) {
		return 1;
	}
	
	public int updateNoofSeats(String restaurantId) {
		return 0;
		
	}
	
	public Menu addMenu(Menu menu) {
		menuItemrepository.save(menu);
		return menu;
		
	}
	
	public int viewRating(String restaurantId) {
		
		
	}
	
	public List<Menu> viewOrder (int orderId){
		return menuItemrepository.findById(orderId);
		
	}
}
