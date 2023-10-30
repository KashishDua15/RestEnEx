package com.fil.RestEnEx1.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.Restaurant;

@Repository
public interface RestaurantDao extends JpaRepository<Restaurant, Long>{
	
}