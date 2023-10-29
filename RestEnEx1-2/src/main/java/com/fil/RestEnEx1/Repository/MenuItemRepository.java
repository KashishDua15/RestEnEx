package com.fil.RestEnEx1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.Menu;
import com.fil.RestEnEx1.entities.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
	
}
