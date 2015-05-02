package com.icf.dao;

import java.util.List;

import com.icf.domain.Folder;
import com.icf.exception.FolderNotExistException;

/**
 * DAO to handle the operations related to Folders.
 */
public interface FolderDAO {
	/**
     * Find folders by user name.
     * 
     * @param username type of string.
     * 
     * @return folder object.
     */	
	public List<Folder> findFoldersByUserName(String username) throws FolderNotExistException;
}


