package com.fil.RestEnEx1.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fil.RestEnEx1.dao.CustomerDao;
import com.fil.RestEnEx1.dao.MenuItemDao;
import com.fil.RestEnEx1.dao.RestEnExOrdersDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItemDTO;
import com.fil.RestEnEx1.entities.RestEnExOrders;
import com.fil.RestEnEx1.entities.Restaurant;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private RestEnExOrdersDao restEnExOrdersDao;
	
	@Autowired
	private MenuItemDao menuitemdao;
	

//	@Override
	public List<Restaurant> getAllRestaurantNames(){
		return restaurantDao.findAll();
	}

	@Override
	public Optional<Restaurant> getRestaurantById(UUID restaurantId) {
		return restaurantDao.findById(restaurantId);
	}

	@Override
	public Customer customerSignIn(String customerEmail, String customerPassword) {
		Customer customer = customerDao.findByCustomerEmail(customerEmail);
		if(customer!=null) {
			if(customer.getCustomerPassword().equals(customerPassword))
				{
					
					return customer;
				}
		}
		return null;
	}

	@Override
	public void customerSignUp(Customer customer) {
		System.out.println("Customer signup"+customer);
		customerDao.saveAndFlush(customer);
	}

	@Override
	public RestEnExOrders bookTable(UUID restaurantId, UUID customerId, RestEnExOrders order) {
	System.out.println(restaurantId+" cID = " + customerId);
		Restaurant restaurant = restaurantDao.findById(restaurantId).get();
		Customer customer = customerDao.findById(customerId).get();
		System.out.println("CUstomer"+customer);
		
		int availableSeats = restaurant.getRestaurantAvailableSeats();
		if(availableSeats>0) {
			restaurant.setRestaurantAvailableSeats(availableSeats-1);
			order.setCustomer(customer);
			order.setRestaurant(restaurant);
			restEnExOrdersDao.saveAndFlush(order);
			return order;
		}
		else 
			return null;
	}

	@Override
	public RestEnExOrders repeatOrder(UUID customerId) {
//		System.out.println(customerId.toString());
//		Customer customer = customerDao.findById(customerId).get();
//		System.out.println("Customer "+customer);
//		List<Order> recentOrders = orderDao.findAllByCustomerId(customerId.toString());
//		Customer customer = customerDao.findById(customerId).get();
//		System.out.println("Customer "+customer);
//		List<RestEnExOrders> recentOrders = restEnExOrdersDao.findAll();
//		System.out.println("recentOrders"+recentOrders);
//		if(recentOrders.isEmpty())
//			return null;
//		else {
//			Order lastOrder = recentOrders.get(0);
//			Restaurant restaurant = lastOrder.getRestaurant();
//			int availableSeats = restaurant.getRestaurantAvailableSeats();
//			if(availableSeats>0) {
//				restaurant.setRestaurantAvailableSeats(availableSeats-1);
//				Order newOrder = new Order(
//						lastOrder.getRestaurantName(),
//						lastOrder.getTableNumber(),
//						lastOrder.getNumberOfPeople(),
//						lastOrder.getBill(),
//						lastOrder.getPaymentStatus(),
//						lastOrder.getRestaurantRating(),
//						lastOrder.getItemsOrdered(),
//						lastOrder.getCustomer(),
//						lastOrder.getRestaurant()
//						);
//				System.out.println("ORDERRRRRRRRRRRR"+newOrder);
//				orderDao.saveAndFlush(newOrder);
//			
//				return newOrder;
//			}
//			else 
				return null;
		
	}

	@Override
	public List<Restaurant> getResstaurantsByArea(String area) {
		// TODO Auto-generated method stub
		List<Restaurant> restaurants = restaurantDao.findAllByRestaurantArea(area);
		System.out.println("HELLOOOo"+restaurants);
		
		return restaurants;
	}

	@Override
	public void addFavourite(UUID customerId, String restaurantName) {
		Restaurant restaurant = restaurantDao.findByRestaurantName(restaurantName);
		Customer customer = customerDao.findById(customerId).get();
		Set<UUID> customerFavourites = customer.getCustomerFavourites();
		customerFavourites.add(restaurant.getRestaurantId());
		customer.setCustomerFavourites(customerFavourites);
		customerDao.saveAndFlush(customer);
		
	}

	@Override
	public List<MenuItemDTO> getMenuByCategory(UUID customerId, String catagory) {
//		System.out.println(customerId+" "+catagory);
		List<MenuItemDTO> menubycategory = menuitemdao.findByCategory(customerId, catagory);
//		System.out.println(menubycategory.toString());
//		for (Object[] menuItem : menubycategory) {
//		    String itemName = (String) menuItem[0];
//		    Float itemPrice = (Float) menuItem[1];
//		    System.out.println("Item Name: " + itemName + ", Item Price: " + itemPrice);
//		}
		return menubycategory;
	}
}
