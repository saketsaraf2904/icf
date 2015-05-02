package com.icf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.icf.domain.Contact;
import com.icf.exception.ContactAlreadyExistException;
import com.icf.exception.ContactsNotExistException;
import com.icf.util.EntityManagerUtil;

/**
 * Contact DAO implementation to handle all the operations related to contact.
 */
public class ContactDAOImpl extends BaseDAO implements ContactDAO {

	/** Logger for logging. */
	private Logger logger = Logger.getLogger(ContactDAOImpl.class);

//	@Override
	public List<Contact> findAllContactsByUserNameForDownload(
			final String username) throws ContactsNotExistException {
		List<Contact> contacts = null;
		Query queryContactsByUserName = getEntityManager().createNamedQuery(
				"findAllContactsByUserNameForDownload");
		queryContactsByUserName.setParameter("userName", username);
		contacts = (List<Contact>) queryContactsByUserName.getResultList();
		if (contacts == null || contacts.size() <= 0) {
			throw new ContactsNotExistException("Contacts Not Exists or Already downloaded.");
		} else {
			for (Contact contact:contacts) {
				contact.setSyncTypeID(1);
				EntityTransaction transaction = EntityManagerUtil
						.getEntityManager().getTransaction();
				transaction.begin();
				getEntityManager().persist(contact);
				transaction.commit();
			}
		}
		
		return contacts;
	}

//	@Override
	public List<Contact> findAllContactsByUserNameAndContactId(
			final String username, final String emailId) {
		List<Contact> contacts = null;
		Query queryContactsByUserName = getEntityManager().createNamedQuery(
				"findAllContactsByUserNameAndContactId");
		queryContactsByUserName.setParameter("userName", username);
		queryContactsByUserName.setParameter("emailId", emailId);

		contacts = (List<Contact>) queryContactsByUserName.getResultList();
		return contacts;
	}

//	@Override
	public List<Contact> uploadContacts(final List<Contact> contacts) {
		List<Contact> finalContacts = new ArrayList<Contact>();
		for (Contact contact : contacts) {
			List<Contact> existContact = findAllContactsByUserNameAndContactId(
					contact.getUserId(), contact.getEmailID());
			if (existContact == null || existContact.size() == 0) {
				EntityTransaction transaction = EntityManagerUtil
						.getEntityManager().getTransaction();
				transaction.begin();
				getEntityManager().persist(contact);
				transaction.commit();
				finalContacts.add(contact);
			}

		}
		return finalContacts;
	}

//	@Override
	public Contact uploadContact(final Contact contact)
			throws ContactAlreadyExistException {
		List<Contact> existContact = findAllContactsByUserNameAndContactId(
				contact.getUserId(), contact.getEmailID());
		if (existContact.size() > 0) {
			throw new ContactAlreadyExistException("Contact Already Exists.");
		}

		logger.info("Existing contact status:"+existContact.size());
		if (existContact == null || existContact.size() == 0) {
			logger.info("Contact save operation start:");
			EntityTransaction transaction = EntityManagerUtil
					.getEntityManager().getTransaction();
			transaction.begin();
			getEntityManager().persist(contact);
			transaction.commit();
			logger.info("Contact save operation end:");
		}
		return contact;
	}
}
