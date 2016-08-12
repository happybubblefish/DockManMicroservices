package com.lin.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.lin.model.User;
import com.lin.model.UserList;

@Controller
public class ConsumerController {
	
	private Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	RestTemplate restTemplate;
	
	private String url = "http://localhost:1112/";
	
	@RequestMapping(value = "{endpointName}/{input}", method = RequestMethod.GET)
	public String hello(@PathVariable String endpointName, @PathVariable String input){
		String response = restTemplate.getForObject("http://localhost:1112/{endpointName}/{input}", String.class, endpointName, input);
		
		return response;
	}
	
	@RequestMapping("/")
	public String hello(){
		return "index";
	}
	
	@RequestMapping("/user/{id}")
	public String user(@PathVariable Long id, Model model){
		User user = null;
		try{
			user = restTemplate.getForObject(url + "user/{id}", User.class, id);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		model.addAttribute("user", user);
		
		return "userView";
	}
	
	private User createUser(){
		User user = new User();
		user.setFirstName("Jack");
		user.setLastName("Tony");
		user.setUserName("MoonFan");
		
		return user;
	}
	
	@RequestMapping(value = "/addUser/{id}")
	public String addUser(@PathVariable Long id, Model model){
		User user = createUser();
		user.setId(id);
		User createdUser = null;
		
		try{
			createdUser = restTemplate.postForObject(url + "addUser", user, User.class);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		model.addAttribute("user", createdUser);
		
		return "userView";
	}
	
	@RequestMapping(value = "/users")
	public String findAllUsers(Model model){
		UserList userList = restTemplate.getForObject(url + "users", UserList.class);
		List<User> users = userList.getUsers();
		
		model.addAttribute("users", users);
		
		return "userListView";
		
	}
	
	@RequestMapping("/editUser/{id}")
	public String preEditUser(@PathVariable Long id, Model model){
		User user = restTemplate.getForObject(url + "user/{id}", User.class, id);
		
		model.addAttribute("user", user);
		
		return "editUserView";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, Model model){
		restTemplate.postForObject(url + "updateUser", user, User.class);
		
		return "redirect:/users";
	}
	
	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id){
		restTemplate.getForObject(url + "deleteUser/{id}", User.class, id);

		return "redirect:/users";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleClientErrors(Exception ex){
		LOGGER.error(ex.getMessage(), ex);
		return ex.getMessage();
	}
}
