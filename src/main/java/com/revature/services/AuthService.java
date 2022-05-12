package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserImpUser;
import com.revature.models.User;

import java.util.Optional;
import java.util.Scanner;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however,
 * additional methods can be added.
 *
 * Examples:
 * <ul>
 * <li>Retrieve Currently Logged-in User</li>
 * <li>Change Password</li>
 * <li>Logout</li>
 * </ul>
 */
public class AuthService {
	private UserDao useDao = new UserImpUser(); 

	/**
	 * <ul>
	 * <li>Needs to check for existing users with username/email provided.</li>
	 * <li>Must throw exception if user does not exist.</li>
	 * <li>Must compare password provided and stored password for that user.</li>
	 * <li>Should throw exception if the passwords do not match.</li>
	 * <li>Must return user object if the user logs in successfully.</li>
	 * </ul>
	 */
	class NotexsituseException extends Exception {
		// Two most common constructors for throwables
		public NotexsituseException() {
			super();
		}

		public NotexsituseException(String message) {
			super(message);
		}
	}

	public User login(String username, String password) {
         User user = useDao.findUserName(username,password);
         if(user.getPassword().equals(password)) {
        	 if(user.getUsername().equals(username)) {
        		 return user;
        	 }
        	
         }
         return null;
	 }
    	
    	
    	
      
  

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
	public User register(User userToBeRegistered) {
		
		User use = new User(userToBeRegistered.getId(),userToBeRegistered.getUsername(),userToBeRegistered.getPassword(),userToBeRegistered.getRole());
		
		if(useDao.addUser(use)) {
			return use;
		}
		
		return null;
		
		
	}

	/**
	 * This is an example method signature for retrieving the currently logged-in
	 * user. It leverages the Optional type which is a useful interface to handle
	 * the possibility of a user being unavailable.
	 */
	public Optional<User> exampleRetrieveCurrentUser() {
		return Optional.empty();
	}

	public void throwChecked() throws NotexsituseException {
		System.out.println("I'm about to throw a checked exception");
		throw new NotexsituseException("An login ist fali");
	}
}
