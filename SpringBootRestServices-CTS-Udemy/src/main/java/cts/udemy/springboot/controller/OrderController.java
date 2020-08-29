package cts.udemy.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cts.udemy.springboot.entity.Order;
import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.OrderNotFoundException;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.repository.OrderRepository;
import cts.udemy.springboot.repository.UserRepository;

@RestController
@RequestMapping(value = "users")
public class OrderController {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private OrderRepository orderrepo;

	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable("userid") int userid) throws UserNotFoundException {
		Optional<User> user = userrepo.findById(userid);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in users repository");
		}
		return user.get().getOrder();
	}

	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable("userid") int userid, @RequestBody Order order)
			throws UserNotFoundException {
		Optional<User> useroptional = userrepo.findById(userid);
		if (!useroptional.isPresent()) {
			throw new UserNotFoundException("User not found in users repository");
		}

		User user = useroptional.get();
		order.setUser(user);
		return orderrepo.save(order);
	}

	/*
	 * @GetMapping("/{userid}/orders/{orderid}") public Optional<Order>
	 * getOrderByOrderId(@PathVariable("userid") int
	 * userid, @PathVariable("orderid") int orderid,
	 * 
	 * @RequestBody Order order) throws OrderNotFoundException { Optional<Order>
	 * orderoptional = orderrepo.findById(orderid); if (!orderoptional.isPresent())
	 * { throw new OrderNotFoundException("Order not found"); }
	 * 
	 * int id=order.getOrderid();
	 * 
	 * return orderoptional; }
	 */

}
