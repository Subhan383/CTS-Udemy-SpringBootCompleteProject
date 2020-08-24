package cts.udemy.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.udemy.springboot.entity.User;
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

	public User createUser(User user) {
		return repo.save(user);
	}

	public User getUserByID(int id) {
		User user = repo.findById(id).get();
		return user;
	}
	
	public User getUserByUsername(String username) {
		return repo.findByUsername(username);
	}

	public User updateUser(int id,User user) {
		user.setId(id);
		return repo.save(user);
	}

	public void deleteUser(int id) {
		repo.deleteById(id);

	}
}
