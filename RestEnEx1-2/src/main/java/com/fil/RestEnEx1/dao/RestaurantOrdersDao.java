package com.fil.RestEnEx1.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.Restaurant;
import com.fil.RestEnEx1.entities.RestaurantOrders;

@Repository
public interface RestaurantOrdersDao extends JpaRepository<RestaurantOrders, UUID> {
	public List<RestaurantOrders> findAllByRestaurantName(String restaurantName);
}
