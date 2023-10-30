package com.fil.RestEnEx1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

}