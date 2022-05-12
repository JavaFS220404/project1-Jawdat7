package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
/**
 * <ul>
 * <li>Should ensure that the username/email provided is unique.</li>
 * <li>Must throw exception if the username/email is not unique.</li>
 * <li>Should persist the user object upon successful registration.</li>
 * <li>Must throw exception if registration is unsuccessful.</li>
 * <li>Must return user object if the user registers successfully.</li>
 * <li>Must throw exception if provided user has a non-zero ID</li>
 * </ul>
 *
 * Note: userToBeRegistered will have an id=0, additional fields may be null.
 * After registration, the id will be a positive integer.
 */

public interface AuthServiveDao {
	public boolean addReim(Reimbursement use);
	public Reimbursement findReimbI(int id);
	public Reimbursement findReimR(String resolver);
	public Reimbursement findReimA(String author);
	
	public boolean upDateReim(Reimbursement reim);
	public List<Reimbursement> findAll();

}
