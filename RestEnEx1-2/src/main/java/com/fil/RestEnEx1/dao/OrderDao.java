package com.fil.RestEnEx1.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, UUID> {
	@Query(value="SELECT * FROM restenx_orders WHERE customer_id=?1",nativeQuery=true)
	public List<Order> findAllByCustomerId(UUID customerId);

}
