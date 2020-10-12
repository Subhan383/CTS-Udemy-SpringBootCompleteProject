package cts.udemy.springboot.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cts.udemy.springboot.dtos.UserDTOV1;
import cts.udemy.springboot.dtos.UserDTOV2;
import cts.udemy.springboot.dtos.UserMmDTO;
import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.service.UserService;

@RestController
@RequestMapping("/versioning/headers/users")
public class UserCustomHeaderVersioningController {

	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper modelMapper;

	// Custom Header Based Versioning - V1
	@GetMapping(value="/{id}",headers="API-VERSION=1")
	public UserDTOV1 getUserByID(@PathVariable("id") @Min(1) int id) throws UserNotFoundException {
		Optional<User> userOptional = service.getUserByID(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}

		User user = userOptional.get();

		UserDTOV1 userDtoV1 = modelMapper.map(user, UserDTOV1.class);

		return userDtoV1;

	}

	// Custom Header Based Versioning - V2
	@GetMapping(value="/{id}",headers="API-VERSION=2")
	public UserDTOV2 getUserByID2(@PathVariable("id") @Min(1) int id) throws UserNotFoundException {
		Optional<User> userOptional = service.getUserByID(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}

		User user = userOptional.get();

		UserDTOV2 userDtoV2 = modelMapper.map(user, UserDTOV2.class);

		return userDtoV2;

	}
}
