package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.models.EnurmReim;
import com.revature.models.EnurmStatus;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;

public class ReimImpReim implements ReimbursementDao {

UserDao userer = new UserImpUser();
	@Override
	public boolean addReim(Reimbursement reim) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO ERS_RIMBUSMENT (REIMB_AMOUNT , REIMB_SUBMITTED, "
					+ "REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID , REIMB_TYPE_ID)"
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			//statement.setInt(++count, 1);
			statement.setDouble(++count, reim.getAmount());
			statement.setTimestamp(++count, reim.getSubmitted());
			statement.setString(++count, reim.getDescription());

			int tempreim = 1;
			//TODO get logged in user's ID
			statement.setInt(++count, tempreim);
			
		
			statement.setInt(++count, 3); // hardcoded as we create with pending always
			
			if (reim.getReimType() == EnurmReim.FOOD) {tempreim = 1;}
			else if(reim.getReimType() == EnurmReim.LODGING) {tempreim = 2;}
			else if(reim.getReimType() == EnurmReim.TRAVEL) {tempreim = 3;}
			else {tempreim = 4;}
			statement.setInt(++count,tempreim);
			
			statement.execute();
			
			return true;
			
		}		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public Reimbursement findReimbI(int id) {
		
		return null;
	}

	@Override
	public Reimbursement findReimR(String resolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement findReimA(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean upDateReim(Reimbursement reim) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE ERS_RIMBUSMENT"
					+ "SET REIMB_RESOLVER = ?, REIMB_RESOLVED = ?, REIMB_STATUS_ID = ?"
					+ "WHERE REIMB_ID = ?;" + "VALUES (?, ?, ?, ?)";

			PreparedStatement prepStatement = conn.prepareStatement(sql);

			int count = 0;

			prepStatement.setInt(++count, reim.getResolver().getId());
		
			//prepStatement.setTimestamp(++count, reim.getResolved());
		//int reimStatus = prepStatement.setInt(++count, 0);
			if(reim.getStatus().equals(Status.APPROVED)) {
				prepStatement.setInt(++count, 1);
				
				
				
			}else {
				reim.getStatus().equals(Status.PENDING);
				prepStatement.setInt(++count, 2);
			}
			
			
			;


			prepStatement.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Reimbursement> getByStatus(Status status) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_RIMBUSMENT WHERE REIMB_STATUS_ID = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);	
			
			
			
			List<Reimbursement> list = new ArrayList<>();
			int statusTypeId;
			switch(status){
			case APPROVED:
				statusTypeId = 1;
				break;
			case DENIED:
				statusTypeId= 2;
				break;
			case PENDING:
				statusTypeId = 3;
				break;
			default:
				statusTypeId= 3;
				break;
		}			
			statement.setInt(1, statusTypeId);						
			ResultSet result = statement.executeQuery();	
			
			while(result.next()) {
		    	Reimbursement reimb = new Reimbursement();
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(result.getInt("REIMB_ID"));
				reimbursement.setAmount(result.getDouble("REIMB_AMOUNT"));
				reimbursement.setSubmitted(result.getTimestamp("REIMB_SUBMITTED"));
				reimbursement.setResolved(result.getTimestamp("REIMB_RESOLVED "));
				reimbursement.setDescription(result.getString("REIMB_DESCRIPTION "));
				
			
				User user = new User();
	            UserDao useDao = new UserImpUser(); 
        		int authorId = result.getInt("REIMB_AUTHOR");
        		user = useDao.findUser(authorId);
        		reimbursement.setAuthor(user);
        		int resolverId = result.getInt("REIMB_RESOLVER");
        		user = useDao.findUser(resolverId);
        		reimbursement.setResolver(user);
        		
        		int reimbStatusID = result.getInt("REIMB_STATUS_ID");
				if(reimbStatusID == 1) {
					reimb.setStatuT(EnurmStatus.APPROVED);
				} else if (reimbStatusID == 2) {
					reimb.setStatuT(EnurmStatus.DENIED);
				} else {
					reimb.setStatuT(EnurmStatus.PENDING);
				}

				int reimbTypeID = result.getInt("REIMB_TYPE_ID");
				if(reimbTypeID == 1) {
					reimb.setReimType(EnurmReim.FOOD);
				} else if (reimbTypeID == 2) {
					reimb.setReimType(EnurmReim.LODGING);
				} else if (reimbTypeID == 3) {
					reimb.setReimType(EnurmReim.TRAVEL);
				} else {
					reimb.setReimType(EnurmReim.OTHER);
				}				
				
				list.add(reimb);

							
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	
    	
        return Collections.emptyList();
    }

	@Override
	public Reimbursement findReimA(Status s, User u, Reimbursement r) {
		return null;
	
			
			

	}

	

		
	}


