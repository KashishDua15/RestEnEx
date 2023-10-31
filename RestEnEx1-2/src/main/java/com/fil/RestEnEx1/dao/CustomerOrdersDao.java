package com.fil.RestEnEx1.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.CustomerOrders;

@Repository
public interface CustomerOrdersDao extends JpaRepository<CustomerOrders, UUID> {
	@Query(value="SELECT * FROM customer_orders WHERE customer_id=?1 ORDER BY date_ordered DESC",nativeQuery=true)
	public List<CustomerOrders> findAllByCustomerId(UUID customerId);
//	@Query(value="SELECT * FROM restenx_orders WHERE payment_status=?1",nativeQuery=true)
//	public List<Order> findAllByPaymentStatus(String status);

}
