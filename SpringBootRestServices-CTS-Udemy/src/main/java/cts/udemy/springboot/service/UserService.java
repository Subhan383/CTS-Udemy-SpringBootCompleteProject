package cts.udemy.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cts.udemy.springboot.entity.User;
import cts.udemy.springboot.exceptions.UserExistsException;
import cts.udemy.springboot.exceptions.UserNotFoundException;
import cts.udemy.springboot.exceptions.UsernameNotFoundException;
import cts.udemy.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		for (User user : repo.findAll()) {
			users.add(user);
		}

		return users;
	}

	public User createUser(User user) throws UserExistsException {
		User existedUser = repo.findByUsername(user.getUsername());
		if (existedUser != null) {
			throw new UserExistsException("User Already Existed, Please Select a Unique Username");
		}
		return repo.save(user);
	}

	public Optional<User> getUserByID(int id) throws UserNotFoundException {
		Optional<User> user = repo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in User DB");
		}
		return user;
	}

	public User getUserByUsername(String username) {

		return repo.findByUsername(username);

	}

	@SuppressWarnings("unlikely-arg-type")
	public User updateUser(int id, User user) throws UserNotFoundException {
		user.setId(id);
		if (!user.equals(id)) {
			throw new UserNotFoundException("User not found in User DB, Please provide the correct UserID");
		}
		return repo.save(user);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void deleteUser(int id) {
		Optional<User> user = repo.findById(id);
		if (!user.equals(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found in User DB, Please provide the correct UserID");
		}
		repo.deleteById(id);

	}

}
