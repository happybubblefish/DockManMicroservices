package com.lin.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
	private List<User> users = new ArrayList<User>();
	
	public UserList(){}
	
	public UserList(List<User> users){
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
