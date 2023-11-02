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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.OwnerService;
import com.fil.RestEnEx1.services.ValidationException;

import jakarta.servlet.http.HttpSession;

@Controller
public class OwnerController {
	
	ObjectMapper mapper = new ObjectMapper();

	
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping("/owner/signup")
	public String ownerSignUp() {
		return"SignUpOwner";
	}
	@PostMapping("/owner/signup")
	public ResponseEntity<HttpStatus> ownerSignUp(@RequestBody Owner owner) throws ValidationException{
		
			ownerService.ownerSignUp(owner);

		return new ResponseEntity<HttpStatus>(HttpStatus.OK );
		}
	
	@GetMapping("/owner/signin")
	public String ownerSignIn() {
		return"SignInOwner";
		}
	
	@PostMapping("/owner/signin")
	public ResponseEntity<HttpStatus> ownerSignIn(@RequestBody LinkedHashMap<String, String> object, HttpSession session){
		Restaurant restaurant = ownerService.ownerSignIn(object.get("email").toString(), object.get("password").toString());
		if(restaurant==null)
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		session.setAttribute("userOwnerRestaurant", restaurant);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK );
	}
	
	@PostMapping("/owner/addrestaurant")
	public ResponseEntity<HttpStatus> addRestaurant(@RequestBody Restaurant restaurant){
		if(ownerService.addRestaurant(restaurant)==null)
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	
	}
	
	@PostMapping("/owner/addmenu")
	public String  addMenu(@RequestBody MenuItem menuItem, HttpSession session){
		Restaurant restaurant = (Restaurant)session.getAttribute("userOwnerRestaurant");
		System.out.println(restaurant.getRestaurantId());
		ownerService.addMenuItem(restaurant.getRestaurantId(), menuItem);
		return "menu added successfully";
		
	}
	
	@GetMapping("/owner/getOrder/{orderId}")
	public ResponseEntity<HttpStatus> getOrder(@RequestParam UUID orderId){
		if(ownerService.getOrder(orderId)!=null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("owner/updateavailableseat")
	public ResponseEntity<HttpStatus> updateAvailableSeats(@RequestBody String availableNoOfSeats, HttpSession session){
		
		try {
			JsonNode availableSeat = mapper.readTree(availableNoOfSeats);
			Restaurant restaurant = (Restaurant)session.getAttribute("userOwnerRestaurant");
			ownerService.updateAvailableSeats(restaurant.getRestaurantId(), Integer.parseInt(availableSeat.get("restaurantAvailableSeats").asText()));
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
	public ResponseEntity<HttpStatus> updateTotalSeats(@RequestBody String updateTotalSeats, HttpSession session){
		
		try {
			JsonNode totalSeat = mapper.readTree(updateTotalSeats);
			Restaurant restaurant = (Restaurant)session.getAttribute("userOwnerRestaurant");
			ownerService.updateAvailableSeats(restaurant.getRestaurantId(), Integer.parseInt(totalSeat.get("restaurantTotalSeats").asText()));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping("owner/getAllOrders")
	public ResponseEntity<HttpStatus> getAllOrder(){
		ownerService.getAllOrders();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}
}




