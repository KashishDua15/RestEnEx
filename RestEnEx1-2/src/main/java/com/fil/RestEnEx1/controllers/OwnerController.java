package com.fil.RestEnEx1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.services.OwnerService;

@RestController
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;

	@PostMapping("/owner/signup")
	public ResponseEntity<HttpStatus> customerSignUp(@RequestBody Owner customer){
		ownerService.ownerSignUp(customer);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK );
	}
	
	@PostMapping("/owner/signin")
	public ResponseEntity<HttpStatus> ownerSignIn(@RequestParam String email, @RequestParam String password){
		if(ownerService.ownerSignIn(email, password)==null)
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK );
	}
}



