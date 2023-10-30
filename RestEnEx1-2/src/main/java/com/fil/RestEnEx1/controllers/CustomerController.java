package com.fil.RestEnEx1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.CustomerService;

@Controller
public class CustomerController {
    
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/signup")
	public String customerSignUp() {
		return "SignUpCustomer";
	}

	@PostMapping("/customer/signup")
	public String customerSignUp(@RequestBody Customer customer) {
		customerService.customerSignUp(customer);
		return "SignUpCustomer";
	}
	
	@GetMapping("/customer/signin")
	public String ownerSignIn() {
		return "SignInCustomer";
	}
	
	@PostMapping("/customer/signin")
	public String customerSignIn(@RequestParam String email, @RequestParam String password) {
		customerService.customerSignIn(email, password);
		return "customersignin";
	}

	@GetMapping("/allNames")
	public String getAllRestaurantNames() {
		customerService.getAllRestaurantNames();
		return "getRestaurant";
	}

	@GetMapping("/allNames/{restaurantId}")
	public String getRestaurantById(@PathVariable long restaurantId) {
		Optional<Restaurant> restaurant = customerService.getRestaurantById(restaurantId);
		return "restaurant";
	}

	@PostMapping("{customerId}/restaurants/{restaurantId}/booktable")
	public String bookTable(@PathVariable long customerId, @PathVariable long restaurantId, @RequestBody Order order) {
		Order orderConfirmed = customerService.bookTable(restaurantId, customerId, order);
		return "bookTable";
	}

}
