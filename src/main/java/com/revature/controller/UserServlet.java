package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet {
	private UserService userService = new UserService();
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();

		System.out.println(uri);

		String[] urlSections = uri.split("/");// should create ["", "check", "home"] or
		// ["", "check", "home", "home name"]

		if (urlSections.length == 3) {
			List<User> list = userService.getAllUsers();

			String json = objectMapper.writeValueAsString(list);

			PrintWriter print = resp.getWriter();
			print.print(json);
			resp.setStatus(200);
			resp.setContentType("application/json");
		} else if (urlSections.length == 4) {
			String spacedName = urlSections[3].replace("%20", " ");
			//User user = userService.getUser("spaceName");
			Optional<User> use = userService.getByUsername(spacedName);
			String json = objectMapper.writeValueAsString(use);

			PrintWriter print = resp.getWriter();
			print.print(json);
			resp.setStatus(200);
			resp.setContentType("application/json");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// information in the body of a request may be spread over separate lines.
		// If this is the case the new line characters will break Jackon's parser.
		// To resolve this, we will read over the whole body and turn it into a
		// single string. A Buffered reader comes with the Request object
		// and can be used to iterate over the body.

		BufferedReader reader = req.getReader();

		StringBuilder stringBuilder = new StringBuilder();

		String line = reader.readLine(); // Gets first line from buffered reader

		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine(); // Gets the next line, returns null at end of body.
		}

		String body = new String(stringBuilder);

		User user = objectMapper.readValue(body, User.class);

		//if (userService.addUser(body, body, body, line, body, 0)) {
			//resp.setStatus(201);
		//} else {
		//	resp.setStatus(406);
		//}
	}


}
