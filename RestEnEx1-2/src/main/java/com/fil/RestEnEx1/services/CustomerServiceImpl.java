package com.fil.RestEnEx1.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fil.RestEnEx1.dao.CustomerDao;
import com.fil.RestEnEx1.dao.MenuItemDao;
import com.fil.RestEnEx1.dao.CustomerOrdersDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.dao.RestaurantOrdersDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItemDTO;
import com.fil.RestEnEx1.entities.CustomerOrders;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.entities.RestaurantOrders;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerOrdersDao customerOrdersDao;
	@Autowired
	private RestaurantOrdersDao restaurantOrdersDao;
	
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
			String encryptedpass = SHA256Util.getSHA256(customerPassword);
			System.out.println("Encrpted"+encryptedpass);
			if(customer.getCustomerPassword().equals(encryptedpass))
				{
					
					return customer;
				}
		}
		return null;
	}

	@Override
	public void customerSignUp(Customer customer) {
		System.out.println("Customer signup"+customer);
		customer.setCustomerPassword(SHA256Util.getSHA256(customer.getCustomerPassword()));
		customerDao.saveAndFlush(customer);
	}

	@Override
	public CustomerOrders bookTable(UUID restaurantId, UUID customerId, CustomerOrders order) {
		Restaurant restaurant = restaurantDao.findById(restaurantId).get();
		Customer customer = customerDao.findById(customerId).get();
		
		order.setRestaurantName(restaurant.getRestaurantName());
		int availableSeats = restaurant.getRestaurantAvailableSeats();
		
		if(availableSeats>0) {
			restaurant.setRestaurantAvailableSeats(availableSeats-1);
			RestaurantOrders newRestaurantOrder = new RestaurantOrders(
					order.getRestaurantName(),
					order.getTableNumber(),
					order.getNumberOfPeople(),
					order.getBill(),
					order.getPaymentStatus(),
					order.getRestaurantRating(),
					order.getItemsOrdered(),
					new Date(),
					customerId,
					restaurant
					);
			order.setCustomer(customer);
			order.setRestaurant_id(restaurantId);
			order.setDateOrdered(new Date());
			
			customerOrdersDao.saveAndFlush(order);
			restaurantOrdersDao.saveAndFlush(newRestaurantOrder);
			return order;
		}
		else 
			return null;
	}

	@Override
	public CustomerOrders repeatOrder(UUID customerId) {
		Customer customer = customerDao.findById(customerId).get();
		List<CustomerOrders> recentOrders = customerOrdersDao.findAllByCustomerId(customerId);
		System.out.println("recentOrders 0"+recentOrders.get(0)+" \nLast "+recentOrders.get(recentOrders.size()-1));
		if(recentOrders.isEmpty())
			return null;
		else {
			CustomerOrders lastOrder = recentOrders.get(0);
			Restaurant restaurant = restaurantDao.findById(lastOrder.getRestaurant_id()).get();
			int availableSeats = restaurant.getRestaurantAvailableSeats();
			if(availableSeats>0) {
				restaurant.setRestaurantAvailableSeats(availableSeats-1);
				CustomerOrders newCustomerOrder = new CustomerOrders(
						lastOrder.getRestaurantName(),
						lastOrder.getTableNumber(),
						lastOrder.getNumberOfPeople(),
						lastOrder.getBill(),
						lastOrder.getPaymentStatus(),
						lastOrder.getRestaurantRating(),
						lastOrder.getItemsOrdered(),
						new Date(),
						lastOrder.getRestaurant_id(),
						lastOrder.getCustomer()
						);
				RestaurantOrders newRestaurantOrder = new RestaurantOrders(
						lastOrder.getRestaurantName(),
						lastOrder.getTableNumber(),
						lastOrder.getNumberOfPeople(),
						lastOrder.getBill(),
						lastOrder.getPaymentStatus(),
						lastOrder.getRestaurantRating(),
						lastOrder.getItemsOrdered(),
						new Date(),
						customerId,
						restaurant
						);
				System.out.println("ORDERRRRRRRRRRRR"+newCustomerOrder);
				customerOrdersDao.saveAndFlush(newCustomerOrder);
				restaurantOrdersDao.saveAndFlush(newRestaurantOrder);
			
				return newCustomerOrder;
			}
			else 
				return null;
		}
		
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

		List<MenuItemDTO> menubycategory = menuitemdao.findByCategory(customerId, catagory);

		return menubycategory;
	}
}
