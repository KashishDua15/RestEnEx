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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.OwnerService;

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
	public ResponseEntity<HttpStatus> ownerSignUp(@RequestBody Owner owner){
		ownerService.ownerSignUp(owner);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK );
		}
	
	@GetMapping("/owner/signin")
	public String ownerSignIn() {
		return"SignInOwner";
		}
	
	@PostMapping("/owner/signin")
	public ResponseEntity<HttpStatus> ownerSignIn(@RequestParam String email, @RequestParam String password){
		if(ownerService.ownerSignIn(email, password)==null)
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK );
	}
	
	@PostMapping("/owner/addrestaurant")
	public ResponseEntity<HttpStatus> addRestaurant(@RequestBody Restaurant restaurant){
		if(ownerService.addRestaurant(restaurant)==null)
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	
	}
	
	@PostMapping("/owner/addmenu/{restaurantId}")
	public String  addMenu(@PathVariable long restaurantId,@RequestBody MenuItem menuItem){
		System.out.println(restaurantId);
		ownerService.addMenuItem(restaurantId, menuItem);
		return "menu added successfully";
		
	}
	
	@GetMapping("/owner/getOrder/{orderId}")
	public ResponseEntity<HttpStatus> getOrder(@RequestParam long orderId){
		if(ownerService.getOrder(orderId)!=null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("owner/updateavailableseat/{restaurantId}")
	public ResponseEntity<HttpStatus> updateAvailableSeats(@PathVariable long restaurantId,@RequestBody String availableNoOfSeats){
		
		try {
			JsonNode availableSeat = mapper.readTree(availableNoOfSeats);
			ownerService.updateAvailableSeats(restaurantId, Integer.parseInt(availableSeat.get("restaurantAvailableSeats").asText()));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}
	
	@PostMapping("owner/updatetotalseat/{restaurantId}")
	public ResponseEntity<HttpStatus> updateTotalSeats(@PathVariable long restaurantId,@RequestBody String updateTotalSeats){
		
		try {
			JsonNode totalSeat = mapper.readTree(updateTotalSeats);
			ownerService.updateAvailableSeats(restaurantId, Integer.parseInt(totalSeat.get("restaurantTotalSeats").asText()));
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




