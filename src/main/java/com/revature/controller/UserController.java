package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

public class UserController {
	private AuthService userService = new AuthService();
	private ObjectMapper mapper = new ObjectMapper();
	UserService userS =new UserService();

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	BufferedReader reader = req.getReader();
		
		StringBuilder stBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line!=null) {
			stBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stBuilder);
		System.out.println(body);
		
		User user = mapper.readValue(body, User.class);
		User authUser = userService.login(user.getPassword(),user.getUsername());
		
		if(!authUser.equals(null)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", authUser);
			resp.setStatus(200);
		}else {
			resp.setStatus(401);
		}

	}
    public void addUser(User use, HttpServletResponse resp) {
		if(userS.addUser(use.getFirstName(), use.getLastName(), use.getEmail(), use.getPhonNum(), use.getAddress(), use.getUser_Role_id())) {
			resp.setStatus(201);
		}else {
			resp.setStatus(400);
		}
	}
}
