package com.fil.RestEnEx1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer/signin")
	public ResponseEntity<HttpStatus> customerSignIn(@RequestParam String email, @RequestParam String password){
		if(customerService.customerSignIn(email, password)==null)
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK );
	}

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



