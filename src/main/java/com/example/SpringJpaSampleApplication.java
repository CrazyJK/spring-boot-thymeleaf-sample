package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.UserRepository;

@SpringBootApplication
public class SpringJpaSampleApplication {
	
	@Autowired
	UserRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(SpringJpaSampleApplication.class, args);
    }
    
    @PostConstruct
    public void init() {
    	
    	for (int i = 0 ; i < 15; i++) {
    		User user = new User();
    		user.setUserName("kim"+ i);
    		user.setNickName("cowboy" + i);
    		user.setAddress("seoul" + i);
    		for (int j=0;j < 10; j++) {
    			user.addOrder(new Order("order" + i,"test" +i,100, user));
    		}
    		repository.save(user);		
    		//repository.flush();
    	}
    
		
		
    }
}
