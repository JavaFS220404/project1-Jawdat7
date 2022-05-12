package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;

public class ReimbursementController {
	private ReimbursementService reimbService = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
	

	public void updatetoReim(Reimbursement reim, HttpSession session, HttpServletResponse resp) throws IOException {
		User user = (User) session.getAttribute("user");
		Reimbursement reimb =  reimbService.process(reim, reim.getStatus(), user);

		if (reimb == null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(200);
			String json = objectMapper.writeValueAsString(reimb);
			PrintWriter print = resp.getWriter();
			print.print(json);
		}
	}
	

}
