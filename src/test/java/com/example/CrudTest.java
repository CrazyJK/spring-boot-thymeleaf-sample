package com.example;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.OrderRepository;
import com.example.service.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringJpaSampleApplication.class)
public class CrudTest {
	
	 private static final Logger log = LoggerFactory.getLogger(CrudTest.class);

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserName("kimhyungsuk");
		user.setNickName("cowboy76");
		user.setAddress("seoul");
		for (int i=0;i < 10; i++) {
			user.addOrder(new Order("order" + i,"test" +i,100, user));
		}
		
		userRepository.save(user);		
		userRepository.flush();
	}
	
	@Test
	public void testFind() throws Exception {
		User findUser = userRepository.findByAddress(user.getAddress());
		log.debug("########findUser {} ",findUser.getNickName());	
	}
	
	@Test
	public void testUpdate() throws Exception {
		user.setAddress("update Address");
		userRepository.saveAndFlush(user);
		User updateUser = userRepository.findOne(user.getId());
		log.debug("########findUser {} ",updateUser.getAddress());	
	}
	
	@Test
	public void testDelete() throws Exception {
		userRepository.delete(user);
		userRepository.flush();
		User updateUser = userRepository.findOne(user.getId());
		assertNull(updateUser);
	}
	
	@Test
	public void testFindAnd() throws Exception {
		Order order = getOrder();
		order = orderRepository.findByOrderNameAndUser(order.getOrderName(), user);
		log.debug("########findUser {} ", order.getNote());
	}
	
	@Test
	public void testFindPage() throws Exception {
		Pageable pageable = new PageRequest(0,5);
		Page<Order> orders = orderRepository.findAll(pageable);
		for (Order order : orders) {

			log.debug("########findUser {} ", order.getNote());
		}
	//	log.debug("########findUser {} ", orders.getContent());
	}

	private Order getOrder() {
		return user.getOrders().get(0);
	}
}
