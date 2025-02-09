package com.test.datapp;

import java.util.ArrayList;
import java.util.List;

public class GetUsersResponse {

	int count=0;
	List<User> users = new ArrayList<>();
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
