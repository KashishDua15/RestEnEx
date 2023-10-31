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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fil.RestEnEx1.dao.CustomerDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.CustomerOrders;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.CustomerService;
import com.fil.RestEnEx1.services.OwnerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/home")
	public String home() {
		return"index";
	}
	
	@GetMapping("/customer/signup")
	public String customerSignUp() {
		return "SignUpCustomer";
	}

	@PostMapping("/customer/signup")
//	public String customerSignUp(@ModelAttribute("customer") Customer customer) {
	public String customerSignUp(@RequestBody Customer customer) {
		System.out.println("Customer signup"+customer);
		customerService.customerSignUp(customer);
		return "SignUpCustomer";
	}
	
	@GetMapping("/customer/signin")
	public String ownerSignIn() {
		return "SignInCustomer";
	}
	
	@PostMapping("/customer/signin")
	public String customerSignIn(@RequestBody LinkedHashMap<String, String> object) {
	Customer customer =	customerService.customerSignIn(object.get("email").toString(), object.get("password").toString());
	if (customer != null) {
        return "SignInCustomer"; 
    } else {
        return "error"; 
    }
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
	public String bookTable(@PathVariable UUID customerId, @PathVariable UUID restaurantId, @RequestBody CustomerOrders order) {
		CustomerOrders orderConfirmed = customerService.bookTable(restaurantId, customerId, order);
		if(orderConfirmed==null)
			return null;
		return "bookTable";

	}
	
	@GetMapping("/{customerId}/repeatOrder")
	public String repeatLastOrder(@PathVariable UUID customerId) {
		CustomerOrders orderConfirmed = customerService.repeatOrder(customerId);
		if(orderConfirmed==null)
			return null;
		return "bookTable";
	}
	
	@GetMapping("/restaurants/{area}")
	public ResponseEntity<List<Restaurant>> getRestaurantByArea(@PathVariable String area) {
		List<Restaurant> restaurants = customerService.getResstaurantsByArea(area);
		System.out.println("REST"+restaurants);
		return ResponseEntity.ok(restaurants); 
	}
	
	@PostMapping("/{customerId}/favourites")
	public String addFavourite(@PathVariable UUID customerId,@RequestBody LinkedHashMap<String, String> object) {
	customerService.addFavourite(customerId, object.get("restaurantName").toString());
	return "";
	}
	

}
