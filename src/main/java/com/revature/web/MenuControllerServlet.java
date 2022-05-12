package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class MenuControllerServlet extends HttpServlet {
	private ObjectMapper mapper = new ObjectMapper();
	private ReimbursementController reimController = new ReimbursementController();
	private UserController userController = new UserController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");

		resp.setStatus(404);

		final String URL = req.getRequestURI().replace("/ERS/core/", "");

		String[] UrlSections = URL.split("/");

		switch (UrlSections[0]) {
		case "login":
			if (req.getMethod().equals("POST")) {
				userController.login(req, resp);
			}
			break;
		case "todos":
			HttpSession session = req.getSession(false);
			if (session != null) {
				if (req.getMethod().equals("GET")) {
					// reimController.getTodoList(session, resp);
				} else if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();

					StringBuilder stBuilder = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						stBuilder.append(line);
						line = reader.readLine();
					}

					String body = new String(stBuilder);
					System.out.println(body);

					User todo = mapper.readValue(body, User.class);
					
				    userController.addUser(todo, resp);
					// reimController(todo, resp);
				} else if (req.getMethod().equals("PUT")) {
					BufferedReader reader = req.getReader();
					StringBuilder stBuilder = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						stBuilder.append(line);
						line = reader.readLine();
					}

					String body = new String(stBuilder);
					System.out.println(body);

					Reimbursement todo = mapper.readValue(body, Reimbursement.class);
					reimController.updatetoReim(todo, session, resp);
				}
			} else {
				resp.setStatus(401);
			}
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
