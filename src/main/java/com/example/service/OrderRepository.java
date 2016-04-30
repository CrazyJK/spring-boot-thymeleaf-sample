package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Order;
import com.example.domain.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	Order findByOrderNameAndUser(String orderName, User user);
	
}
