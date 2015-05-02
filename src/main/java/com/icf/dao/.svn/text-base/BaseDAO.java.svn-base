package com.icf.dao;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.icf.util.EntityManagerUtil;

/**
 * Generic base dao for all the DAO implementation like user.
 */
public class BaseDAO {

	/** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(BaseDAO.class);

	/**
	 * Method to get the entity manager.
	 * 
	 * @return entity manager
	 */
	public EntityManager getEntityManager() {
		if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("BaseDAO::getEntityManager method - START");
        }
		return EntityManagerUtil.getEntityManager();
	}

}
