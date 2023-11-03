package com.fil.RestEnEx1.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.MenuItem;
import com.fil.RestEnEx1.entities.MenuItemDTO;
import com.fil.RestEnEx1.entities.Restaurant;

@Repository
public interface MenuItemDao extends JpaRepository<MenuItem , UUID>{
//	@Query(nativeQuery = true, value = "select item_name from menu_item where restaurant_id = UUID_TO_BIN("?") and category = ?")
//	public List<MenuItem> findByCategory(UUID restaurantId,String category);
	
//	@Query("SELECT item_name FROM menu_item WHERE restaurant_id = UUID_TO_BIN(:restaurantId) AND category = :category")
	@Query("SELECT new com.fil.RestEnEx1.entities.MenuItemDTO(m.itemName,m.itemPrice) FROM MenuItem m WHERE m.restaurant.id = :restaurantId AND m.category = :category")
	List<MenuItemDTO> findByCategory(@Param("restaurantId") UUID restaurantId, @Param("category") String category);
	List<MenuItem> findAllByRestaurant(Restaurant restaurant);
	
}
