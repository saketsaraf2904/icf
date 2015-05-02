package com.icf.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * JPA Entity manager utility.
 * 
 * @author samavish
 *
 */
public class EntityManagerUtil
{
	/** Logger for logging. */
	private static final Logger LOGGER = Logger.getLogger(EntityManagerUtil.class);
	
	/** Entity manager */
	private static EntityManager em;
	
	/**
	 * Create and returns instance of entity manager.
	 * @return
	 */
   public static EntityManager getEntityManager()
   {
	   if (LOGGER.isDebugEnabled()) {
           LOGGER.debug("EntityManagerUtil:: getEntityManager method - START");
       }
	  if(em == null) {
		  em = Persistence.createEntityManagerFactory("cafe-invest-ds").createEntityManager();
	  }
	  
	  if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("EntityManagerUtil:: getEntityManager method - END");
      }
      return em;
   }
}
