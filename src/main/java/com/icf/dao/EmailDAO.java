package com.icf.dao;

import java.util.List;

import com.icf.domain.Attachment;
import com.icf.domain.Email;
import com.icf.exception.EmailAlreadyExistException;
import com.icf.exception.EmailNotExistException;

/**
 * DAO to handle the operations related to Emails.
 */
/**
 * @author Saket
 *
 */
public interface EmailDAO {

	/**
	 * Find emails by outlook email id.
	 * @param outlookEmailId
	 * @return
	 */
	Email findByOutlookEmailId(Integer outlookEmailId);

	/**
	 * Create Email.
	 * @param email
	 * @param attachments
	 * @return
	 * @throws EmailAlreadyExistException
	 */
	Email createEmail(Email email, List<Attachment> attachments)
			throws EmailAlreadyExistException;

	/**
	 * Find email Ids by user name.
	 * @param userId
	 * @return
	 * @throws EmailNotExistException
	 */
	List<Integer> findEmailIdsByUserName(String userId)
			throws EmailNotExistException;
	
	/**
	 * Find emails by user name.
	 * 
	 * @param userName
	 *            type of string.
	 * 
	 * @return list object.
	 */
	public List<Email> findAllEmailsByUserNameForDownload(String username)
			throws EmailNotExistException;
	
	/**
	 * Find Attachments by email ID.
	 * 
	 * @param emailId
	 *            type of string.
	 * 
	 * @return list object.
	 */
	public List<Attachment> findAllAttachmentsByEmailIdForDownload(String emailId);
}
