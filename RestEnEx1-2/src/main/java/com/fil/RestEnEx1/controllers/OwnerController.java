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
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.services.OwnerService;

@Controller
public class OwnerController {
	
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
	public ResponseEntity<HttpStatus> addRestaurant(@RequestParam Restaurant restaurant){
		if(ownerService.addRestaurant(restaurant)==null)
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	
	}
	
	@PostMapping("/owner/addmenu")
	public ResponseEntity<HttpStatus> addMenu(@RequestParam int restaurantId,@RequestParam List<MenuItem> menuItem){
		if(ownerService.addMenu(restaurantId, menuItem)!=null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		}
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
	public ResponseEntity<HttpStatus> updateAvailableSeats(@RequestParam long restaurantId,int availableNoOfSeats){
		ownerService.updateAvailableSeats(restaurantId, availableNoOfSeats);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}
	
	@PostMapping("owner/updatetotalseat/{restaurantId}")
	public ResponseEntity<HttpStatus> updateTotalSeats(@RequestParam long restaurantId,@RequestParam int updateTotalSeats){
		ownerService.updateTotalSeats(restaurantId, updateTotalSeats);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping("owner/getAllOrders")
	public ResponseEntity<HttpStatus> getAllOrder(){
		ownerService.getAllOrders();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}

}




