package cts.udemy.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cts.udemy.springboot.dtos.UserMsDTO;
import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.mappers.UserMapper;
import cts.udemy.springboot.repository.UserRepository;

@RestController
@RequestMapping("/mapstructs/users")
public class UserMapStructController {

	@Autowired
	private UserRepository repo;

	@Autowired
	private UserMapper mapper;

	@GetMapping
	public List<UserMsDTO> getAllUsersMapStruct() {
		return mapper.userToUserMsDTOS(repo.findAll());
	}

	@GetMapping("/{id}")
	public UserMsDTO getUserByIdMapStruct(@PathVariable int id) {
		Optional<User> userOptional = repo.findById(id);
		User user = userOptional.get();
		return mapper.userToUserMsDTO(user);
	}

}
