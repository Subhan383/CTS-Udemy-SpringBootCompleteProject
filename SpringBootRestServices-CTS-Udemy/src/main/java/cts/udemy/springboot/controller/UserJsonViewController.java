package cts.udemy.springboot.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import cts.udemy.springboot.entity.JsonViews;
import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.service.UserService;

@RestController
@RequestMapping(value = "/jsonview/users")
@Validated
public class UserJsonViewController {

	@Autowired
	private UserService service;

	@GetMapping("/external/{id}")
	@JsonView(JsonViews.External.class)
	public Optional<User> getUserByIDExternal(@PathVariable("id") @Min(1) int id) throws UserNotFoundException {
		try {
			return service.getUserByID(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	@GetMapping("/internal/{id}")
	@JsonView(JsonViews.Internal.class)
	public Optional<User> getUserByIDInternal(@PathVariable("id") @Min(1) int id) throws UserNotFoundException {
		try {
			return service.getUserByID(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

}
