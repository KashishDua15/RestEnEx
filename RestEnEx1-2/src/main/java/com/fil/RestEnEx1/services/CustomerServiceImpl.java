package com.fil.RestEnEx1.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

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
	public List<Restaurant> getAllRestaurants(){
		return restaurantDao.findAll();
	}

	@Override
	public Restaurant getRestaurantById(UUID restaurantId) {
		return restaurantDao.findById(restaurantId).get();
	}

	@Override
	public Customer customerSignIn(String customerEmail, String customerPassword) {
		Customer customer = customerDao.findByCustomerEmail(customerEmail);
		System.out.println("Signin"+customer);
		if(customer!=null) {
			String encryptedpass = SHA256Util.getSHA256(customerPassword);
			System.out.println("Encrpted"+encryptedpass);
			if(customer.getCustomerPassword().equals(encryptedpass))
				{
				System.out.println("Signin2"+customer);
					return customer;
				}
		}
		return null;
	}

	@Override
	public void customerSignUp(Customer customer) throws ValidationException {
		System.out.println("Customer signup"+customer);
		if(!isValidEmail(customer.getCustomerEmail()))
		{
			throw new ValidationException("Please enter a valid email. ");
		}
		else if(!isValidPassword(customer.getCustomerPassword()))
		{
			throw new ValidationException("Please enter a valid password. The password must contain at least one digit.\r\n"
					+ "The password must contain at least one lowercase English character.\r\n"
					+ "The password must contain at least one uppercase English character.\r\n"
					+ "The password must contain at least one special character.\r\n"
					+ "The password must not contain any whitespace.\r\n"
					+ "The password must be between 8 and 20 characters long.");
		}
		else if (!isValidMobileNumber(customer.getCustomerContactNumber()))
		{
			throw new ValidationException("Please enter a valid contact number. ");
		}
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
					order.getDateBookedFor(),
					order.getTimeBookedFor(),
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
						lastOrder.getDateBookedFor(),
						lastOrder.getTimeBookedFor(),
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
						lastOrder.getDateBookedFor(),
						lastOrder.getTimeBookedFor(),
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
	public Customer addFavourite(UUID customerId, String restaurantName) {
		Customer customer = customerDao.findById(customerId).get();
		Set<String> customerFavourites = customer.getCustomerFavourites();
		customerFavourites.add(restaurantName);
		customer.setCustomerFavourites(customerFavourites);
		customerDao.saveAndFlush(customer);
		return customer;
		
	}

	@Override
	public List<MenuItemDTO> getMenuByCategory(UUID customerId, String catagory) {

		List<MenuItemDTO> menubycategory = menuitemdao.findByCategory(customerId, catagory);

		return menubycategory;
	}

	
	
	private boolean isValidEmail(String email) {
	    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	    Pattern pat = Pattern.compile(emailRegex);
	    if (email == null)
	        return false;
	    return pat.matcher(email).matches();
	}

	private boolean isValidPassword(String password) {

	    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
	    Pattern pat = Pattern.compile(passwordRegex);
	    if (password == null)
	        return false;
	    return pat.matcher(password).matches();
	}

	private boolean isValidMobileNumber(String mobileNumber) {
	
	    String mobileNumberRegex = "^[6-9]\\d{9}$";
	    Pattern pat = Pattern.compile(mobileNumberRegex);
	    if (mobileNumber == null)
	        return false;
	    return pat.matcher(mobileNumber).matches();

}
  
  
	@Override
	public List<CustomerOrders> getOrderHistory(UUID customerId) {
		List<CustomerOrders> orderHistory = customerOrdersDao.findAllByCustomerId(customerId);
		return orderHistory;
	}
  
}
