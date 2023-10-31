package com.fil.RestEnEx1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.dao.MenuItemDao;
import com.fil.RestEnEx1.dao.OwnerDao;
import com.fil.RestEnEx1.dao.CustomerOrdersDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.CustomerOrders;
import com.fil.RestEnEx1.entities.Restaurant;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	
	@Autowired 
	private CustomerOrdersDao customerOrdersDao;
	@Autowired 
	private OwnerDao ownerDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private MenuItemDao menuitemdao;
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurantDao.saveAndFlush(restaurant);
		return restaurant;
		
		
	}
	
	public Restaurant updateAvailableSeats(UUID restaurantId,int availableNoOfSeats) {
		
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantAvailableSeats(availableNoOfSeats);
		return restaurantDao.saveAndFlush(res);

	}
	
	public Restaurant updateTotalSeats(UUID restaurantId, int updateTotalSeats) {
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantTotalSeats(updateTotalSeats);
		return restaurantDao.saveAndFlush(res);	
	}
	

	public void addMenuItem(UUID restaurantId,MenuItem menuItem) {
		Restaurant res=restaurantDao.findById(restaurantId).get();
		System.out.println(res);
		menuItem.setRestaurant(res);
		System.out.println(menuItem.getRestaurant());
		menuitemdao.saveAndFlush(menuItem);
	}
	
	public int getRating(UUID restaurantId) {
		
		Restaurant res = restaurantDao.findById(restaurantId).get();
		return res.getResturantRating();	
	}
	
	public CustomerOrders getOrder (UUID orderId){
		return customerOrdersDao.findById(orderId).get();
		
	}
	
	public List<CustomerOrders> getAllOrders(){
		return customerOrdersDao.findAll();
		
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
		System.out.println(owner.getRestaurant());
		Restaurant r = owner.getRestaurant();
		restaurantDao.saveAndFlush(r);
		ownerDao.saveAndFlush(owner);
	}



}
