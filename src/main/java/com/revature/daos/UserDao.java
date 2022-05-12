package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	public boolean addUser(User use);
	public User findUser(int id);
	public User findUser(String email);
	public User findUserName(String username);
	public User findUserName(String username, String password);
	
	
	public boolean upDateuser(User use);
	public List<User> findAll();
	
	

}
