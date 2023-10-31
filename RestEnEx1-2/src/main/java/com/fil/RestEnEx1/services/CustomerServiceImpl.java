package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	public Optional<Restaurant> getRestaurantById(UUID restaurantId) {
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
		customerDao.saveAndFlush(customer);
	}

	@Override
	public Order bookTable(UUID restaurantId, UUID customerId, Order order) {
	System.out.println(restaurantId+" cID = " + customerId);
		Restaurant restaurant = restaurantDao.findById(restaurantId).get();
		Customer customer = customerDao.findById(customerId).get();
		
		int availableSeats = restaurant.getRestaurantAvailableSeats();
		if(availableSeats>0) {
			restaurant.setRestaurantAvailableSeats(availableSeats-1);
			order.setCustomer(customer);
			order.setRestaurant(restaurant);
			orderDao.saveAndFlush(order);
			return order;
		}
		else 
			return null;
	}

	@Override
	public Order repeatOrder(UUID customerId) {
		List<Order> recentOrders = orderDao.findAllByCustomerId(customerId);
		if(recentOrders.isEmpty())
			return null;
		else {
			Order lastOrder = recentOrders.get(0);
			Order newOrder = new Order(
					lastOrder.getRestaurantName(),
					lastOrder.getTableNumber(),
					lastOrder.getNumberOfPeople(),
					lastOrder.getBill(),
					lastOrder.getPaymentStatus(),
					lastOrder.getRestaurantRating(),
					lastOrder.getItemsOrdered(),
					lastOrder.getCustomer(),
					lastOrder.getRestaurant()
					);
			System.out.println("ORDERRRRRRRRRRRR"+newOrder);
			orderDao.saveAndFlush(newOrder);
		
			return newOrder;
		}
	}
}
