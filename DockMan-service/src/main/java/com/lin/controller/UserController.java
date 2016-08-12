package com.lin.controller;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.lin.model.User;
import com.lin.model.UserList;
import com.lin.service.UserService;

@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String main(){
		return "index";
	}
	
	@RequestMapping("/user/{id}")
	@ResponseBody 
	public User getUser(@PathVariable Long id, Model model){
		LOGGER.info("Get user with id {}", id);
		User user = userService.findUserById(id);
		Validate.isTrue(user != null, "Unable to find user with id: " + id);
		model.addAttribute("user", user);
		
		return user;
	}
	
	@RequestMapping("/users")
	@ResponseBody
	public UserList findAllUsers(){
		LOGGER.info("List all the users.");
		return new UserList(userService.findAllUsers());
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String preAddUser(Model model){
		User newUser = new User();
		model.addAttribute("newUser", newUser);
		
		return "newUserView";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public User addUser(@RequestBody User user){
		LOGGER.info("Create new use {}", user);
		return userService.save(user);
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public User updateUser(@RequestBody User user){
		LOGGER.info("Update user {}", user);
		
		User updatedUser = userService.findUserById(user.getId());
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setUserName(user.getUserName());
		
		return userService.save(updatedUser);
	}
	
	
	@RequestMapping("/deleteUser/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id){
		User user = userService.findUserById(id);
		LOGGER.info("Delete user with id {}", user.getId());
		
		userService.delete(user);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleServerErrors(Exception ex){
		LOGGER.info(ex.getMessage(), ex);
		return ex.getMessage();
	}
}
