package com.fil.RestEnEx1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/allNames")
	public List<Restaurant>getAllRestaurantNames(){
		System.out.println("Hi");
		return customerService.getAllRestaurantNames();
	}
	
	@GetMapping("/allNames/{restaurantId}")
	public ResponseEntity<Optional<Restaurant>> getRestaurantById(@PathVariable String restaurantId){
		Optional<Restaurant> restaurant = customerService.getRestaurantById(restaurantId);
		if(restaurant != null) {
			return ResponseEntity.ok(restaurant);}
			else {
				return ResponseEntity.notFound().build();
			}
		}
	}



