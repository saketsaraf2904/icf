package com.icf.dao;

import java.util.List;

import com.icf.domain.User;
import com.icf.exception.UserNotExistException;

/**
 * DAO to handle the operations related to user.
 */
public interface UserDAO {
	/**
     * Validate user account.
     * 
     * @param user of type user object.
     * 
     * @return user object.
     */
	public User validateUser(User user) throws UserNotExistException;


	/**
     * Find all the users.
     * 
     * @return user list.
     */
	public List<User> findAll();
	
	
	/**
     * Find user by user name.
     * 
     * @param username type of string.
     * @param password type of string.
     * 
     * @return user object.
     */	
	public User findByName(String username, String password);
}


