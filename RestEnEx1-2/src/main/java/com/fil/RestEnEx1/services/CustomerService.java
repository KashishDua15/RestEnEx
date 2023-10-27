package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.entities.Restaurant;

public interface CustomerService {
	

	public List<Restaurant> getAllRestaurantNames();
	public Optional<Restaurant> getRestaurantById(String restaurantId);



}
