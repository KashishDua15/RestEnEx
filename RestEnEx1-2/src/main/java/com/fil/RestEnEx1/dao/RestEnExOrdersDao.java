package com.fil.RestEnEx1.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.RestEnExOrders;

@Repository
public interface RestEnExOrdersDao extends JpaRepository<RestEnExOrders, UUID> {
//	@Query(value="SELECT * FROM restenx_orders WHERE customer_id=UUID_TO_BIN(?1)",nativeQuery=true)
//	public List<Order> findAllByCustomerId(String customerId);
//	@Query(value="SELECT * FROM restenx_orders WHERE payment_status=?1",nativeQuery=true)
//	public List<Order> findAllByPaymentStatus(String status);

}
