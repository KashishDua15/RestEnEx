package com.fil.RestEnEx1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.dao.MenuItemDao;
import com.fil.RestEnEx1.dao.OrderDao;
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
	private OrderDao orderDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private MenuItemDao menuitemdao;
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurantDao.save(restaurant);
		return restaurant;
		
		
	}
	
	public Restaurant updateAvailableSeats(long restaurantId,int availableNoOfSeats) {
		
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantAvailableSeats(availableNoOfSeats);
		return restaurantDao.save(res);

	}
	
	public Restaurant updateTotalSeats(long restaurantId, int updateTotalSeats) {
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantTotalSeats(updateTotalSeats);
		return restaurantDao.save(res);	
	}
	

	public void addMenuItem(long restaurantId,MenuItem menuItem) {
		Restaurant res=restaurantDao.findById(restaurantId).get();
		System.out.println(res);
		menuItem.setRestaurant(res);
		System.out.println(menuItem.getRestaurant());
		menuitemdao.save(menuItem);
	}
	
	public int getRating(long restaurantId) {
		
		Restaurant res = restaurantDao.findById(restaurantId).get();
		return res.getResturantRating();	
	}
	
	public Order getOrder (long orderId){
		return orderDao.findById(orderId).get();
		
	}
	
	public List<Order> getAllOrders(){
		return orderDao.findAll();
		
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
		restaurantDao.save(r);
		ownerDao.save(owner);
	}


}
