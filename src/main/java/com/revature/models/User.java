package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 *     
 * </ul>
 *
 */
       
       
public class User extends AbstractUser {
	  private String firstName;
      private String lastName;
      private String email;
      private String phonNum;
      private String address;
      private int user_Role_id;

    public User() {
        super();
    }
    

    public User(String firstName, String lastName, String email, String phonNum, String address,int user_Role_id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phonNum = phonNum;
		this.address = address;
		this.user_Role_id= user_Role_id;
		
	}
    


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhonNum() {
		return phonNum;
	}


	public void setPhonNum(String phonNum) {
		this.phonNum = phonNum;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getUser_Role_id() {
		return user_Role_id;
	}


	public void setUser_Role_id(int user_Role_id) {
		this.user_Role_id = user_Role_id;
	}


	/**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    
    
    public User(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }


}
