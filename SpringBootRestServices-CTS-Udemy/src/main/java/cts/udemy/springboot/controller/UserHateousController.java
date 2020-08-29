package cts.udemy.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cts.udemy.springboot.entity.Order;
import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.repository.UserRepository;
import cts.udemy.springboot.service.UserService;

@RestController
@RequestMapping(value = "/hateous/users")
@Validated
public class UserHateousController {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private UserService userservice;

	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> allusers = userservice.getAllUsers();

		for (User user : allusers) {
			int userid = user.getUserid();
			Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			CollectionModel<Order> orders = ControllerLinkBuilder.methodOn(OrderHateousController.class)
					.getAllOrders(userid);
			Link orderslink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderslink);
		}
		Link getAllUsersLink = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finaluser = new CollectionModel<User>(allusers, getAllUsersLink);
		return finaluser;
	}

	@GetMapping("/{id}")
	public EntityModel<User> getUserByID(@PathVariable("id") @Min(1) int userid) throws UserNotFoundException {
		try {

			Optional<User> useroptional = userservice.getUserByID(userid);
			User user = useroptional.get();
			int id = user.getUserid();

			Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(id).withSelfRel();
			user.add(selflink);
			EntityModel<User> finaluser = new EntityModel<User>(user);
			return finaluser;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

}
