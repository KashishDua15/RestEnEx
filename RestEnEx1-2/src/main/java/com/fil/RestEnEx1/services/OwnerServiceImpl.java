package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.dao.OwnerDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	
	@Autowired 
	private OwnerDao ownerDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurantDao.save(restaurant);
		return restaurant;
		
		
	}
	
	public int updateAvailableSeats(String restaurantId,int availableNoOfSeats) {
		
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantAvailableSeats(availableNoOfSeats);
		return res.getRestaurantAvailableSeats();

	}
	
	public int updateTotalSeats(String restaurantId, int updateTotalSeats) {
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantTotalSeats(updateTotalSeats);
		return res.getRestaurantTotalSeats();	
	}
	

	
	public MenuItem addMenu(MenuItem menuItem) {
		ownerDao.save(menuItem);
		return menuItem;
		
	}
	
	public int viewRating(String restaurantId) {
		return 0;
		
		
	}
	
	public Optional<Menu> viewOrder (int orderId){
		return ownerDao.findById(orderId);
		
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

	@Override
	public void ownerSignUp(Owner owner) {
		ownerDao.save(owner);
	}
}
