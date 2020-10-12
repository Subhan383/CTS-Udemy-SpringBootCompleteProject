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

import cts.udemy.springboot.dtos.UserMmDTO;
import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.service.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/{id}")
	public UserMmDTO getUserByID(@PathVariable("id") @Min(1) int id) throws UserNotFoundException {
		Optional<User> userOptional = service.getUserByID(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}

		User user = userOptional.get();

		UserMmDTO userMmDto = modelMapper.map(user, UserMmDTO.class);

		return userMmDto;

	}
}