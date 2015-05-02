package com.icf.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.icf.domain.User;
import com.icf.exception.UserNotExistException;

/**
 * User DAO implementation to handle all the operations related to user.
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

	/** Logger for logging. */
	private Logger logger = Logger.getLogger(UserDAOImpl.class);

	/**
	 * Find user by user id.
	 * 
	 * @param id
	 *            of type integer.
	 * @return user object.
	 */
	public User findById(final int id) {
		return null;
	}

	// @Override
	public User validateUser(final User user) throws UserNotExistException {
		User existingUser = findByName(user.getUsername(), user.getPassword());

		if (existingUser == null) {
			throw new UserNotExistException("User Not Exists.");
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	// @Override
	public List<User> findAll() {
		Query queryUsers = getEntityManager().createNamedQuery("findAllUsers");
		List<User> users = (List<User>) queryUsers.getResultList();
		return users;
	}

	// @Override
	public User findByName(final String username, final String password) {
		User user = null;
		Query queryUsersByUserName = getEntityManager().createNamedQuery(
				"findAllUsersByUserName");
		queryUsersByUserName.setParameter("userName", username);
		queryUsersByUserName.setParameter("password", password);

		try {
			user = (User) queryUsersByUserName.getSingleResult();
		} catch (NoResultException nre) {
			logger.error("No user exists for username :: " + username);
		}
		return user;
	}
}
