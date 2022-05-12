package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;


public interface ReimbursementDao {
	public boolean addReim(Reimbursement use);
	public Reimbursement findReimbI(int id);
	public Reimbursement findReimR(String resolver);
	public Reimbursement findReimA(String author);
	
	public boolean upDateReim(Reimbursement reim);
	public List<Reimbursement> getByStatus(Status status) ;
	public Reimbursement findReimA(Status s, User u, Reimbursement r);
	

}
