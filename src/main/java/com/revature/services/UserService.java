package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.daos.UserDao;
import com.revature.daos.UserImpUser;
import com.revature.models.User;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {
	private UserDao useDao = new UserImpUser(); 
   private String user;
  
   

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> getByUsername(String username) {
		
		User use = useDao.findUserName(username);
		Optional<User> userr = Optional.of(use);
		return userr;
	}
	public String  getUser() {
	
		return user;
		
	}
	public String toString() {
		 return " User " +  getUser();
	}
	public List<User> getAllUsers(){
		return useDao.findAll();
	}
	public boolean addUser(String firstName,String lastName, String email,String phonNum, String addres,int user_id_Role) {
		User use = new User(firstName,lastName,email,phonNum,addres, user_id_Role);
		if(useDao.addUser(use)) {
			return true;
		}
		return false;
		
		
	}
}
