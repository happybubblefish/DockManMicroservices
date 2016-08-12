package com.lin;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.lin.controller.UserController;
import com.lin.model.User;
import com.lin.model.UserList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DockManServiceApplicationTests {

	private String url = "http://localhost:1112/";
	private RestTemplate restTemplate;

	public DockManServiceApplicationTests() {
		this.restTemplate = new RestTemplate();
	}

	@Autowired
	private UserController userController;

	@Test
	public void testGetUser() {
		url = url + "user/1";
		User user = restTemplate.getForObject(url, User.class);

		Assert.assertNotNull(user);
		Assert.assertEquals("Lin", user.getFirstName());
	}

	@Test
	public void testUserList() {
		UserList userList = restTemplate.getForObject(url + "users", UserList.class);
		List<User> users = userList.getUsers();

		Assert.assertNotNull(userList);
		Assert.assertNotEquals(0L, users.size());
	}
}
