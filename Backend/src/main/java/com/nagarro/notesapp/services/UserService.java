package com.nagarro.notesapp.services;

import com.nagarro.notesapp.models.User;

import java.util.List;

public interface UserService {
	public User createUser(User user) throws Exception;

	public User save(User user);

	public User showUser(String email);

	public User fetchUserByEmailAndPassword(String email, String password);

	public List<User> findAll();

}
