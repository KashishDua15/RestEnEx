package com.fil.RestEnEx1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.Menu;

@Repository
public interface MenuItemRepository extends JpaRepository<Menu, Integer>{
	
}
