package com.icf.dao;

import java.util.List;

import com.icf.domain.Contact;
import com.icf.exception.ContactAlreadyExistException;
import com.icf.exception.ContactsNotExistException;

/**
 * DAO to handle the operations related to Contacts.
 */
public interface ContactDAO {
	/**
	 * Find contacts by user name.
	 * 
	 * @param userName
	 *            type of string.
	 * 
	 * @return folder object.
	 */
	public List<Contact> findAllContactsByUserNameForDownload(String username)
			throws ContactsNotExistException;

	/**
	 * Find contacts by user name.
	 * 
	 * @param userName
	 *            type of string.
	 * @param contactId
	 *            type of Integer
	 * 
	 * @return folder object.
	 */
	public List<Contact> findAllContactsByUserNameAndContactId(String username,
			String emailId);

	/**
	 * @param contacts
	 * @return
	 */
	public List<Contact> uploadContacts(List<Contact> contacts);

	/**
	 * @param contact
	 * @return
	 * @throws ContactAlreadyExistException
	 */
	public Contact uploadContact(Contact contact)
			throws ContactAlreadyExistException;
}
