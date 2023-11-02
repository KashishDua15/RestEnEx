package com.fil.RestEnEx1.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.CustomerMainPageDetails;
import com.fil.RestEnEx1.entities.CustomerOrders;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.OwnerMainPageDetails;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.entities.RestaurantOrders;
import com.fil.RestEnEx1.services.OwnerService;
import com.fil.RestEnEx1.services.ValidationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OwnerController {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private OwnerService ownerService;
	private OwnerMainPageDetails ownerDetails = new OwnerMainPageDetails();

	@GetMapping("/owner/signup")
	public String ownerSignUp() {
		return "SignUpOwner";
	}

	@PostMapping("/owner/signup")

	public String ownerSignUp(@ModelAttribute("owner") Owner owner) throws ValidationException {
		System.out.println("Owner signup" + owner);
		ownerService.ownerSignUp(owner);

		return "SignUpOwner";
	}

	@GetMapping("/owner/home")
	public ModelAndView home(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ownerDetails", (OwnerMainPageDetails) session.getAttribute("ownerDetails"));

		modelAndView.setViewName("OwnerMainPage");
		return modelAndView;
	}

	@GetMapping("/owner/signin")
	public String ownerSignIn() {
		return "SignInOwner";
	}

	@PostMapping("/owner/signin")
	public String ownerSignIn(@RequestParam String emailId, @RequestParam String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Restaurant restaurant = ownerService.ownerSignIn(emailId, password);
		if (restaurant != null) {
			Owner owner = restaurant.getOwner();
			List<RestaurantOrders> orderHistory = ownerService.getAllOrders(restaurant.getRestaurantName());
			ownerDetails.setOwner(owner);
			ownerDetails.setResOrderHistory(orderHistory);

			session.setAttribute("userOwnerRestaurant", ownerDetails); // Store owner details

			return "OwnerMainPage";
		}
		return "error";
	}

	@PostMapping("/owner/addrestaurant")
	public ResponseEntity<HttpStatus> addRestaurant(@RequestBody Restaurant restaurant) {
		if (ownerService.addRestaurant(restaurant) == null)
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	@GetMapping("/owner/addmenu")
	public String addMenu() {
		return "MenuItem";
	}

	@PostMapping("/owner/addmenu")
	public String addMenu(@RequestBody MenuItem menuItem, HttpSession session) {
		Restaurant restaurant = (Restaurant) session.getAttribute("userOwnerRestaurant");
		System.out.println(restaurant.getRestaurantId());
		ownerService.addMenuItem(restaurant.getRestaurantId(), menuItem);

		return "menu added successfully";

	}

	@GetMapping("/owner/getOrder/{orderId}")
	public ResponseEntity<HttpStatus> getOrder(@RequestParam UUID orderId) {
		if (ownerService.getOrder(orderId) != null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} else {
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("owner/updateavailableseat")
	public ResponseEntity<HttpStatus> updateAvailableSeats(@RequestBody String availableNoOfSeats,
			HttpSession session) {

		try {
			JsonNode availableSeat = mapper.readTree(availableNoOfSeats);
			Restaurant restaurant = (Restaurant) session.getAttribute("userOwnerRestaurant");
			ownerService.updateAvailableSeats(restaurant.getRestaurantId(),
					Integer.parseInt(availableSeat.get("restaurantAvailableSeats").asText()));

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

	@PostMapping("owner/updatetotalseat")
	public ResponseEntity<HttpStatus> updateTotalSeats(@RequestBody String updateTotalSeats, HttpSession session) {

		try {
			JsonNode totalSeat = mapper.readTree(updateTotalSeats);

			Restaurant restaurant = (Restaurant) session.getAttribute("userOwnerRestaurant");
			ownerService.updateAvailableSeats(restaurant.getRestaurantId(),
					Integer.parseInt(totalSeat.get("restaurantTotalSeats").asText()));

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

//	@GetMapping("owner/getAllOrders")
//	public ResponseEntity<HttpStatus> getAllOrder() {
//		ownerService.getAllOrders();
//		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//
//	}
}
