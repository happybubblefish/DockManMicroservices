package com.lin.service;

import java.util.List;

import com.lin.model.User;

public interface UserService {

	public User save(User user);
	
	public void delete(User user);
	
	public List<User> findAllUsers();
	
	public User findUserById(Long id);
	
	public List<User> findByUserName(String username);
}
