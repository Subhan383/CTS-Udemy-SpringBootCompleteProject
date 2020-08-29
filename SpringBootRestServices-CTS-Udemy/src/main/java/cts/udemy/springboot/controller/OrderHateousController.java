package cts.udemy.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cts.udemy.springboot.entity.Order;
import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.repository.UserRepository;

@RestController
@RequestMapping(value = "/hateous/orders")
@Validated
public class OrderHateousController {

	@Autowired
	private UserRepository userrepo;

	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable("userid") int userid) throws UserNotFoundException {
		Optional<User> user = userrepo.findById(userid);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in users repository");
		}

		List<Order> allorders = user.get().getOrder();
		CollectionModel<Order> finalResources = new CollectionModel<Order>(allorders);

		return finalResources;
	}

}
