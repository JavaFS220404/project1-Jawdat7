package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserImpUser implements UserDao {

	public User findUser(String email) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ERS_USERS  WHERE USER_EMAIL = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, email);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				User use = new User();
				use.setUsername(result.getString("ERS_USERNAME"));

				use.setPassword(result.getString("ERS_PASSWORD"));
				use.setUser_Role_id(result.getInt("USER_ROLE_ID"));
				int userRoleId = result.getInt("USER_ROLE_ID");
				if(userRoleId ==1) {
					use.setRole(Role.EMPLOYEE);
					
				}

				return use;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public User findUserName(String username) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ERS_USERS  WHERE ERS_USERNAME= ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, username);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				User use = new User();
				use.setUsername(result.getString("ERS_USERNAME"));

				use.setPassword(result.getString("ERS_PASSWORD"));
				// use.setRole(null);

				return use;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean addUser(User use) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO ERS_USERS (ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, "
					+ "USER_LAST_NAME , USER_EMAIL,ERS_PHON_NUMBER, ERS_USER_ADDRESS,USER_ROLE_ID)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, use.getUsername());
			statement.setString(++count, use.getPassword());
			statement.setString(++count, use.getFirstName());
			statement.setString(++count, use.getLastName());
			statement.setString(++count, use.getEmail());
			statement.setString(++count, use.getPhonNum());
			statement.setString(++count, use.getAddress());
			int roleID;
			if (use.getRole() == Role.EMPLOYEE) {
				roleID = 1;
			} else {
				roleID = 2;
			}
			statement.setInt(++count, roleID);
			
			statement.execute();
			
			
			return true;
			
		}		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
    	
    	
    }

	

	public User findUser(int id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?;";			
			PreparedStatement statement = conn.prepareStatement(sql);			
			statement.setInt(1, id);						
			ResultSet result = statement.executeQuery();
	    	User user = new User();
			while(result.next()) {
				user.setId(result.getInt("ERS_USERS_ID"));
				user.setUsername(result.getString("ERS_USERNAME"));
				user.setPassword(result.getString("ERS_PASSWORD"));
				user.setFirstName(result.getString("USER_FIRST_NAME"));
				user.setLastName(result.getString("USER_LAST_NAME"));
				user.setEmail(result.getString("USER_EMAIL"));
				
				int userRoleId = result.getInt("USER_ROLE_ID");
				if(userRoleId == 2) {
					user.setRole(Role.FINANCE_MANAGER);
				} else {
					user.setRole(Role.EMPLOYEE);
				}
								
				return user;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		

    	
	    return null;
	}

	public boolean upDateuser(User use) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE ERS_USERS SET ERS_USERNAME=?, ERS_PASSWORD=?, "
					+ "USER_EMAIL=?, USER_FIRST_NAME=?, USER_LAST_NAME=?, USER_ROLE_ID=? "
					+ "WHERE ERS_USERS_ID = ?";

			PreparedStatement statement = conn.prepareStatement(sql);

			int count = 0;

			statement.setString(++count, use.getPassword());
			statement.setString(++count, use.getUsername());
			statement.setInt(++count, use.getId());
			

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> findAll() {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ERS_USERS ;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<User> list = new ArrayList<>();

			while (result.next()) {
				User use = new User();
			

				use.setPassword(result.getString("ERS_PASSWORD"));
				use.setUsername(result.getString("ERS_USERNAME"));
				use.setId(result.getInt("ERS_USERS_ID"));
				use.setFirstName(result.getString("USER_FIRST_NAME"));
				use.setLastName(result.getString("USER_LAST_NAME"));
				use.setEmail(result.getString("USER_EMAIL"));
				
				list.add(use);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public User findUserName(String username, String password) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID  = ? ERS_PASSWORD  = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, username);
			statement.setString(1, password);
			

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				User use = new User();
				use.setUsername(result.getString("ERS_USERNAME "));

				use.setPassword(result.getString("ERS_PASSWORD"));
				

				return use;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
