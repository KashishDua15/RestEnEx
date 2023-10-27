package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.dao.CustomerDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.Restaurant;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private CustomerDao customerDao;
	

//	@Override
	public List<Restaurant> getAllRestaurantNames(){
		return restaurantDao.findAll();
	}

	@Override
	public Optional<Restaurant> getRestaurantById(String restaurantId) {
		return restaurantDao.findById(restaurantId);
	}

	@Override
	public Customer customerSignIn(String email, String password) {
		Customer customer = customerDao.findByCustomerEmail(email);
		if(customer!=null) {
			if(customer.getCustomerPassword().equals(password))
				return customer;
		}
		return null;
	}
}
