package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.Repository.MenuItemRepository;
import com.fil.RestEnEx1.dao.OwnerDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.Menu;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	@Autowired 
	private MenuItemRepository menuItemrepository;
	@Autowired 
	private OwnerDao ownerDao;
	public Restaurant addRestaurant(Restaurant restaurant) {
//		menuItemrepository.save(restaurant);
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
		return 0;
		
		
	}
	
	public Optional<Menu> viewOrder (int orderId){
		return menuItemrepository.findById(orderId);
		
	}

	@Override
	public Owner ownerSignIn(String email, String password) {
		Owner owner = ownerDao.findByEmailId(email);
		if(owner!=null) {
			if(owner.getPassword().equals(password))
				return owner;
		}
		return null;
		
	}
}
