package com.fil.RestEnEx1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.RestEnEx1.dao.MenuItemDao;
import com.fil.RestEnEx1.dao.OwnerDao;
import com.fil.RestEnEx1.dao.CustomerOrdersDao;
import com.fil.RestEnEx1.dao.RestaurantDao;
import com.fil.RestEnEx1.dao.RestaurantOrdersDao;
import com.fil.RestEnEx1.entities.Customer;
import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.Owner;
import com.fil.RestEnEx1.entities.CustomerOrders;
import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.entities.RestaurantOrders;

import jakarta.transaction.Transactional;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	
	@Autowired 
	private CustomerOrdersDao customerOrdersDao;
	
	@Autowired
	private RestaurantOrdersDao restaurantOrdersDao;
	
	@Autowired 
	private OwnerDao ownerDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private MenuItemDao menuitemdao;
	
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurantDao.saveAndFlush(restaurant);
		return restaurant;
		
		
	}
	
	public Restaurant updateAvailableSeats(UUID restaurantId,int availableNoOfSeats) {
		
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantAvailableSeats(availableNoOfSeats);
		return restaurantDao.saveAndFlush(res);

	}
	
	public Restaurant updateTotalSeats(UUID restaurantId, int updateTotalSeats) {
		Restaurant res=restaurantDao.findById(restaurantId).get();
		res.setRestaurantTotalSeats(updateTotalSeats);
		return restaurantDao.saveAndFlush(res);	
	}
	

	public void addMenuItem(UUID restaurantId,MenuItem menuItem) {
		Restaurant res=restaurantDao.findById(restaurantId).get();
		System.out.println(res);
		menuItem.setRestaurant(res);
		System.out.println(menuItem.getRestaurant());
		menuitemdao.saveAndFlush(menuItem);
	}
	
	public int getRating(UUID restaurantId) {
		
		Restaurant res = restaurantDao.findById(restaurantId).get();
		return res.getResturantRating();	
	}
	
	public CustomerOrders getOrder (UUID orderId){
		return customerOrdersDao.findById(orderId).get();
		
	}
	
	public List<RestaurantOrders> getAllOrders(String restaurantName){
		return restaurantOrdersDao.findAllByRestaurantName(restaurantName);
		
	}
	

	@Override
	public Restaurant ownerSignIn(String email, String password) {
		Owner owner = ownerDao.findByEmailId(email);
		if(owner!=null) {
			String encryptedpass = SHA256Util.getSHA256(password);
			System.out.println("Encrpted"+encryptedpass);
			if(owner.getPassword().equals(encryptedpass))
				return owner.getRestaurant();

		}
		return null;
		
	}

	@Override
	public void ownerSignUp(Owner owner) throws ValidationException {
		System.out.println(owner.getRestaurant());
		Restaurant r = owner.getRestaurant();
		
		if(!isValidEmail(owner.getEmailId()))
		{
			throw new ValidationException("Please enter a valid email. ");
		}
		else if(!isValidPassword(owner.getPassword()))
		{
			throw new ValidationException("Please enter a valid password. The password must contain at least one digit.\r\n"
					+ "The password must contain at least one lowercase English character.\r\n"
					+ "The password must contain at least one uppercase English character.\r\n"
					+ "The password must contain at least one special character.\r\n"
					+ "The password must not contain any whitespace.\r\n"
					+ "The password must be between 8 and 20 characters long.");
		}
		else if (!isValidMobileNumber(owner.getContactno()))
		{
			throw new ValidationException("Please enter a valid contact number. ");
		}
		else
		{
      r.setOwner(owner);
			restaurantDao.saveAndFlush(r);
			owner.setPassword(SHA256Util.getSHA256(owner.getPassword()));
			ownerDao.saveAndFlush(owner);
		}
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



}
