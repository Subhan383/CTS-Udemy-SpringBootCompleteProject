package cts.udemy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}

	@GetMapping("/users/{id}")
	public User getUserByID(@PathVariable("id") int id) {
		return service.getUserByID(id);
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) {
		return service.getUserByUsername(username);
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user,@PathVariable("id") int id) {
		return service.updateUser(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		service.deleteUser(id);
	}
}
