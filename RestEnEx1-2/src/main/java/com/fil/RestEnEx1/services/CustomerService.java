package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.entities.Customer;

import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.MenuItemDTO;

import com.fil.RestEnEx1.entities.CustomerOrders;

import com.fil.RestEnEx1.entities.Restaurant;

public interface CustomerService {
	
	public void customerSignUp(Customer customer);
	public Customer customerSignIn(String email, String password);
	public List<Restaurant> getAllRestaurantNames();
	public List<Restaurant> getResstaurantsByArea(String area);
	public Optional<Restaurant> getRestaurantById(UUID restaurantId);
	public CustomerOrders bookTable(UUID restaurantId, UUID customerId, CustomerOrders order);
	public CustomerOrders repeatOrder(UUID customerId);
	public void addFavourite(UUID customerId,String restaurantName);
	public List<MenuItemDTO> getMenuByCategory(UUID customerId,String catagory);

}
