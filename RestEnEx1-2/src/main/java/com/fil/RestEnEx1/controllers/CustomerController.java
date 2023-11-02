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
import org.springframework.web.servlet.ModelAndView;

import com.fil.RestEnEx1.dao.CustomerDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.CustomerMainPageDetails;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.MenuItemDTO;

import com.fil.RestEnEx1.entities.CustomerOrders;

import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.CustomerService;
import com.fil.RestEnEx1.services.OwnerService;
import com.fil.RestEnEx1.services.ValidationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	private CustomerMainPageDetails customerDetails = new CustomerMainPageDetails() ;
	
	@GetMapping("/home")
	public ModelAndView home(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("customerDetails", (CustomerMainPageDetails)session.getAttribute("customerDetails"));
	    
	    modelAndView.setViewName("CustomerMainPage");
	    return modelAndView;
	}
	
	@GetMapping("/customer/signup")
	public String customerSignUp() {
		return "SignUpCustomer";
	}

	@PostMapping("/customer/signup")
	public String customerSignUp(@ModelAttribute("customer") Customer customer) throws ValidationException {
	//public String customerSignUp(@RequestBody Customer customer) {

		System.out.println("Customer signup"+customer);
		customerService.customerSignUp(customer);
		return "CustomerMainPage";
	}
	
	@GetMapping("/customer/signin")
	public String customerSignIn() {
		return "SignInCustomer";
	}
	
	@PostMapping("/customer/signin")
	public String ownerSignIn(@RequestParam String customerEmail, @RequestParam String customerPassword,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer =	customerService.customerSignIn(customerEmail,customerPassword);
	if (customer != null) {
		customerDetails.setCustomer(customer);
		customerDetails.setOrderHistory(customerService.getOrderHistory(customer.getCustomerId()));
		customerDetails.setRestaurants(customerService.getAllRestaurants());
		session.setAttribute("customerDetails", customerDetails);
		System.out.println("Session"+session.getAttribute("customerDetails"));
		return "CustomerMainPage"; 
    } else {
    	return "error";
    }
	}

	@GetMapping("/restaurants")
	public String getAllRestaurantNames() {
		customerService.getAllRestaurants();
		return "getRestaurant";
	}

	@GetMapping("/restaurants/{restaurantId}")
	public String getRestaurantById(@PathVariable UUID restaurantId) {
		Optional<Restaurant> restaurant = customerService.getRestaurantById(restaurantId);
		return "restaurant";
	}

	@PostMapping("/restaurants/{restaurantId}/booktable")
	public String bookTable(@PathVariable UUID restaurantId, @RequestBody CustomerOrders order, HttpSession session) {
		System.out.println("Session set"+session.getAttribute("customerDetails"));
		CustomerMainPageDetails customerDetails = (CustomerMainPageDetails)session.getAttribute("customerDetails");
		System.out.println("Session set"+customerDetails);
		Customer customer = customerDetails.getCustomer();
		UUID id = UUID.fromString("f15b51a2-fc9d-4e1e-82d5-bb81e86d8009");
		CustomerOrders orderConfirmed = customerService.bookTable(restaurantId, id, order);
		if(orderConfirmed==null)
			return null;
		return "bookTable";

	}
	@GetMapping("/repeatOrder")
	public String repeatLastOrder(HttpSession session) {
		CustomerMainPageDetails customerDetails = (CustomerMainPageDetails)session.getAttribute("customerDetails");
		Customer customer = customerDetails.getCustomer();
		System.out.println("FUCKKK"+customerDetails);
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
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    // Invalidate the session
	    session.invalidate();
	    return "logged_out";
	}
	

}