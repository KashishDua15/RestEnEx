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

import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.MenuItemDTO;

import com.fil.RestEnEx1.entities.CustomerOrders;

import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.CustomerService;
import com.fil.RestEnEx1.services.OwnerService;

import jakarta.servlet.http.HttpSession;

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
	public String customerSignIn(@RequestBody LinkedHashMap<String, String> object,  HttpSession session) throws Exception {
	Customer customer =	customerService.customerSignIn(object.get("email").toString(), object.get("password").toString());
	if (customer != null) {
		session.setAttribute("userCustomer", customer);
        return "SignInCustomer"; 
    } else {
    	 throw new Exception("Not a valid user");
    }
	}

	@GetMapping("/restaurants")
	public String getAllRestaurantNames() {
		customerService.getAllRestaurantNames();
		return "getRestaurant";
	}

	@GetMapping("/restaurants/{restaurantId}")
	public String getRestaurantById(@PathVariable UUID restaurantId) {
		Optional<Restaurant> restaurant = customerService.getRestaurantById(restaurantId);
		return "restaurant";
	}

	@PostMapping("/restaurants/{restaurantId}/booktable")
	public String bookTable(@PathVariable UUID restaurantId, @RequestBody CustomerOrders order, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("userCustomer");
		CustomerOrders orderConfirmed = customerService.bookTable(restaurantId, customer.getCustomerId(), order);
		if(orderConfirmed==null)
			return null;
		return "bookTable";

	}
	@GetMapping("/repeatOrder")
	public String repeatLastOrder(HttpSession session) {
		Customer customer = (Customer)session.getAttribute("userCustomer");
		CustomerOrders orderConfirmed = customerService.repeatOrder(customer.getCustomerId());
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
	
	@PostMapping("/favourites")
	public String addFavourite(@RequestBody LinkedHashMap<String, String> object, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("userCustomer");
		customerService.addFavourite(customer.getCustomerId(), object.get("restaurantName").toString());
		return "";
	}
	
	@GetMapping("/menufilter/{restaurantId}")
	public ResponseEntity<List<MenuItemDTO>> getMenuByCategory(@PathVariable UUID restaurantId, @RequestBody String category)
	{
		List<MenuItemDTO> menu = customerService.getMenuByCategory(restaurantId, category);
		System.out.println(menu);
		return ResponseEntity.ok(menu);//menu;
	}
	

}
