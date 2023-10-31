package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.RestEnExOrders;
import com.fil.RestEnEx1.entities.Restaurant;

public interface CustomerService {
	
	public void customerSignUp(Customer customer);
	public Customer customerSignIn(String email, String password);
	public List<Restaurant> getAllRestaurantNames();
	public Optional<Restaurant> getRestaurantById(UUID restaurantId);
	public RestEnExOrders bookTable(UUID restaurantId, UUID customerId, RestEnExOrders order);
	public RestEnExOrders repeatOrder(UUID customerId);

}
