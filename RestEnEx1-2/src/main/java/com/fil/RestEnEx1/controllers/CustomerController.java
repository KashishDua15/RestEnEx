package com.fil.RestEnEx1.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	public String customerSignIn(@RequestBody LinkedHashMap<String, String> object) {
		if(customerService.customerSignIn(object.get("email").toString(), object.get("password").toString())==null)
			return "";
		return "customersignin";
	}

	@GetMapping("/allNames")
	public String getAllRestaurantNames() {
		customerService.getAllRestaurantNames();
		return "getRestaurant";
	}

	@GetMapping("/allNames/{restaurantId}")
	public String getRestaurantById(@PathVariable UUID restaurantId) {
		Optional<Restaurant> restaurant = customerService.getRestaurantById(restaurantId);
		return "restaurant";
	}

	@PostMapping("/{customerId}/restaurants/{restaurantId}/booktable")
	public Order bookTable(@PathVariable UUID customerId, @PathVariable UUID restaurantId, @RequestBody Order order) {
		Order orderConfirmed = customerService.bookTable(restaurantId, customerId, order);
		if(orderConfirmed==null)
			return null;
//		return "bookTable";
		return orderConfirmed;
	}
	
	@GetMapping("/{customerId}/repeatOrder")
	public Order repeatLastOrder(@PathVariable UUID customerId) {
		Order orderConfirmed = customerService.repeatOrder(customerId);
		if(orderConfirmed==null)
			return null;
		return orderConfirmed;
//		return "bookTable";
	}

}
