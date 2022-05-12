package com.revature;


import java.util.List;
import java.util.Scanner;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class Driver {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		UserService us = new UserService()	;
		AuthService as = new AuthService()	;
		Reimbursement rm = new Reimbursement()	;
		ReimbursementService rs = new ReimbursementService();
		
		
		System.out.println(us.addUser("jojo","fayad","ojo@gmail.com", "22002", "hildesheim", 1));
		//
		System.out.println("Welcome to the super secret shield database.");
	}
}
