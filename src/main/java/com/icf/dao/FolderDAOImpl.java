package com.icf.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.icf.domain.Folder;
import com.icf.exception.FolderNotExistException;

/**
 * Folder DAO  implementation to handle all the operations related to folder.
 */
public class FolderDAOImpl extends BaseDAO implements FolderDAO {
	
	 /** Logger for logging. */
	private Logger logger = Logger.getLogger(FolderDAOImpl.class);
	

	//@Override
	public List<Folder> findFoldersByUserName(final String username) throws FolderNotExistException{
		List<Folder> folder  = null;
		Query queryFoldersByUserName = getEntityManager().createNamedQuery("findAllFoldersByUserName");
		queryFoldersByUserName.setParameter("userName", username);
		folder = (List<Folder>) queryFoldersByUserName.getResultList();
		if(folder == null || folder.size()<= 0) {
			throw new FolderNotExistException("Folder tree Not Exists.");
		}
		return folder;
	}
	
}
