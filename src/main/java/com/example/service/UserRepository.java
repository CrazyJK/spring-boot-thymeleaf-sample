package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByAddress(String address);
}
