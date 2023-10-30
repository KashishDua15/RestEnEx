package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.dao.CustomerDao;
import com.fil.RestEnEx1.dao.OrderDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.Order;
import com.fil.RestEnEx1.entities.Restaurant;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OrderDao orderDao;
	

//	@Override
	public List<Restaurant> getAllRestaurantNames(){
		return restaurantDao.findAll();
	}

	@Override
	public Optional<Restaurant> getRestaurantById(long restaurantId) {
		return restaurantDao.findById(restaurantId);
	}

	@Override
	public Customer customerSignIn(String email, String password) {
		Customer customer = customerDao.findByCustomerEmail(email);
		if(customer!=null) {
			if(customer.getCustomerPassword().equals(password))
				{
					
					return customer;
				}
		}
		return null;
	}

	@Override
	public void customerSignUp(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public Order bookTable(long restaurantId, long customerId, Order order) {
		Restaurant restaurant = restaurantDao.findById(restaurantId).get();
		Customer customer = customerDao.findById(customerId).get();
		int availableSeats = restaurant.getRestaurantAvailableSeats();
		if(availableSeats>0) {
			restaurant.setRestaurantAvailableSeats(availableSeats-1);
			order.setCustomer(customer);
			order.setRestaurant(restaurant);
			orderDao.save(order);
			return order;
		}
		else 
			return null;
	}
}
