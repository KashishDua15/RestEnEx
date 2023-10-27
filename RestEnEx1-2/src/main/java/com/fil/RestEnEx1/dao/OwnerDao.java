package com.fil.RestEnEx1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fil.RestEnEx1.entities.Owner;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Integer>{
	public Owner findByEmailId(String email);
	
}