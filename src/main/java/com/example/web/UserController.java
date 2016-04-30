package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.UserRepository;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
public class UserController {
	
	@Autowired
	UserRepository repository;
	
	@RequestMapping("/list")
	public String list(Model model, Pageable pageable) {
		
		Page<User> list = repository.findAll(pageable);
		model.addAttribute("list", list);
	
		return "user/list";
	}
}
