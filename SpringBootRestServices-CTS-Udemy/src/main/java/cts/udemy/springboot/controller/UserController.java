package cts.udemy.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.UserExistsException;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.exceptions.UsernameNotFoundException;
import cts.udemy.springboot.service.UserService;

@RestController
@Validated
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder uri)
			throws UserExistsException {
		try {
			service.createUser(user);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uri.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(header, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.UPGRADE_REQUIRED, ex.getMessage());
		}

	}

	@GetMapping("/users/{id}")
	public Optional<User> getUserByID(@PathVariable("id") @Min(1) int id) throws UserNotFoundException {
		try {
			return service.getUserByID(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	@GetMapping("/users/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) throws UsernameNotFoundException {
		User user = service.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User: '" + username + "' not found in user repository");
		}
		return user;
	}

	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") int id) throws UserNotFoundException {
		try {
			return service.updateUser(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		service.deleteUser(id);
	}

}
