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
	private UUID restaurantIdForBooking;
	
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
		return "SignInCustomer";
	}
	
	@GetMapping("/customer/signin")
	public String customerSignIn() {
		return "SignInCustomer";
	}
	@PostMapping("/customer/signin")
	public ModelAndView ownerSignIn(@RequestParam String customerEmail, @RequestParam String customerPassword,HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		Customer customer =	customerService.customerSignIn(customerEmail,customerPassword);
	if (customer != null) {
		customerDetails.setCustomer(customer);
		customerDetails.setOrderHistory(customerService.getOrderHistory(customer.getCustomerId()));
		customerDetails.setRestaurants(customerService.getAllRestaurants());
		session.setAttribute("customerDetails", customerDetails);
		System.out.println("Session"+session.getAttribute("customerDetails"));

	    modelAndView.addObject("customerDetails", customerDetails);
	    modelAndView.setViewName("CustomerMainPage");
		return modelAndView; 
    } else {
    	modelAndView.setViewName("error");
    	return modelAndView;
    }
	}

	@GetMapping("/restaurants")
	public String getAllRestaurantNames() {
		customerService.getAllRestaurants();
		return "getRestaurant";
	}

	@GetMapping("/restaurants/{restaurantId}")
	public String getRestaurantById(@PathVariable UUID restaurantId) {
		Restaurant restaurant = customerService.getRestaurantById(restaurantId);
		return "restaurant";
	}
	
	@GetMapping("/restaurants/booktable/{restaurantId}")
	public ModelAndView customerRestaurantPage(@PathVariable String restaurantId, HttpSession sessions) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("Session"+sessions.getAttribute("customerDetails"));
		customerDetails.setRestaurant(customerService.getRestaurantById(UUID.fromString(restaurantId)));
		modelAndView.addObject("customerDetails", customerDetails);
		System.out.println("RESTAURANTID"+restaurantId+" "+customerDetails);
		restaurantIdForBooking = UUID.fromString(restaurantId);
		modelAndView.setViewName("index");
		
		return modelAndView;
	}

	@PostMapping("/restaurants/booktable")
	public ModelAndView bookTable(@ModelAttribute("order") CustomerOrders order, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		CustomerMainPageDetails customerDetails = (CustomerMainPageDetails)session.getAttribute("customerDetails");
		System.out.println("Session set"+modelAndView.getModelMap()+" "+session.getAttribute("customerDetails"));

		Customer customer = customerDetails.getCustomer();
		modelAndView.addObject("customerDetails", customerDetails);
		modelAndView.setViewName("index");
		CustomerOrders orderConfirmed = customerService.bookTable(restaurantIdForBooking, customer.getCustomerId(), order);
		if(orderConfirmed==null)
			return null;
		return modelAndView;

	}
	@GetMapping("/repeatOrder")
	public String repeatLastOrder(HttpSession session) {
		CustomerMainPageDetails customerDetails = (CustomerMainPageDetails)session.getAttribute("customerDetails");
		Customer customer = customerDetails.getCustomer();
		CustomerOrders orderConfirmed = customerService.repeatOrder(customer.getCustomerId());
		if(orderConfirmed==null)
			return null;
		return "bookTable";
	}
	
	@PostMapping("/restaurants/area")
	public ModelAndView getRestaurantByArea(@RequestParam String area) {
		System.out.println("AREAAA"+area);
		List<Restaurant> restaurants;
		if(area.equalsIgnoreCase("") || area==null)
			restaurants = customerService.getAllRestaurants();	
		else
			restaurants = customerService.getResstaurantsByArea(area);
			
		ModelAndView modelAndView = new ModelAndView();
		customerDetails.setRestaurants(restaurants);
	    modelAndView.addObject("customerDetails", customerDetails);
	    System.out.println("MODEL AND VIEWW"+modelAndView.getModelMap());
	    modelAndView.setViewName("CustomerMainPage");
		return modelAndView; 
	}
	
	@PostMapping("/addtofavourites")
	public ModelAndView addFavourite(@RequestParam String restaurantName, HttpSession session) {
		CustomerMainPageDetails customerDetails = (CustomerMainPageDetails)session.getAttribute("customerDetails");
		Customer customer = customerDetails.getCustomer();
		ModelAndView modelAndView = new ModelAndView();
		customerDetails.setCustomer(customerService.addFavourite(customer.getCustomerId(), restaurantName ));
		modelAndView.addObject("customerDetails", customerDetails);
		System.out.println("Favourites Customer Details"+customerDetails);
		modelAndView.setViewName("CustomerMainPage");
		return modelAndView;
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