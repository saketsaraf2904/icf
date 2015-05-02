package com.icf.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.icf.domain.Attachment;
import com.icf.domain.Email;
import com.icf.exception.EmailAlreadyExistException;
import com.icf.exception.EmailNotExistException;
import com.icf.util.EntityManagerUtil;

/**
 * Email DAO implementation to handle all the operations related to folder.
 */
public class EmailDAOImpl extends BaseDAO implements EmailDAO {

	/** Logger for logging. */
	private Logger logger = Logger.getLogger(EmailDAOImpl.class);

	// @Override
	public Email createEmail(final Email email,
			final List<Attachment> attachments)
			throws EmailAlreadyExistException {
		EntityTransaction transaction = EntityManagerUtil.getEntityManager()
				.getTransaction();

		Email existingEmail = findByOutlookEmailId(email.getOutlookEmailId());
		logger.info("Existing email status:" + existingEmail);
		if (existingEmail != null) {
			throw new EmailAlreadyExistException("Email Already Exists.");
		}

		logger.info("Starting email save operation:");
		transaction.begin();
		// Set<Attachment> attachmentsSet = new
		// HashSet<Attachment>(attachments);
		getEntityManager().persist(email);
		logger.info("Ending email save operation:");

		logger.info("Starting attachment save operation:");

		if (attachments != null) {
			for (Attachment attachment : attachments) {
				attachment.setEmailId(email.getId());
				getEntityManager().persist(attachment);
			}
		}
		transaction.commit();
		logger.info("Ending attachment save operation:");

		return email;
	}

	// @Override
	public Email findByOutlookEmailId(final Integer outlookEmailId) {
		Email email = null;
		Query queryUsersByUserName = getEntityManager().createNamedQuery(
				"findAllEmailByOutlookEmailId");
		queryUsersByUserName.setParameter("outlookEmailId", outlookEmailId);
		try {
			email = (Email) queryUsersByUserName.getSingleResult();
		} catch (NoResultException nre) {
			logger.error("No email exists for outlookEmailId :: "
					+ outlookEmailId);
		}
		return email;
	}

	// @Override
	public List<Integer> findEmailIdsByUserName(final String username)
			throws EmailNotExistException {
		List<Integer> emailIds = null;
		Query queryContactsByUserName = getEntityManager().createNamedQuery(
				"findAllEmailIdsByUserName");
		queryContactsByUserName.setParameter("userName", username);

		emailIds = (List<Integer>) queryContactsByUserName.getResultList();

		if (emailIds.size() <= 0) {
			throw new EmailNotExistException("Email Ids not exist.");
		}
		return emailIds;
	}

	// @Override
	public List<Email> findAllEmailsByUserNameForDownload(final String username)
			throws EmailNotExistException {
		List<Email> emails = null;
		Query queryContactsByUserName = getEntityManager().createNamedQuery(
				"findAllEmailsByUserNameForDownload");
		queryContactsByUserName.setParameter("userName", username);
		emails = (List<Email>) queryContactsByUserName.getResultList();
		if (emails == null || emails.size() <= 0) {
			throw new EmailNotExistException(
					"Emails Not Exists or Already downloaded.");
		} else {
			for (Email email : emails) {
				email.setSyncTypeId(1);
				EntityTransaction transaction = EntityManagerUtil
						.getEntityManager().getTransaction();
				transaction.begin();
				getEntityManager().persist(email);
				transaction.commit();
			}
		}

		return emails;
	}

	// @Override
	public List<Attachment> findAllAttachmentsByEmailIdForDownload(
			final String emailId) {
		List<Attachment> attachments = null;
		Query queryAttachmentsByEmailId = getEntityManager().createNamedQuery(
				"findAllAttachmentsByEmailIdForDownload");
		queryAttachmentsByEmailId.setParameter("emailId",
				Integer.parseInt(emailId));
		attachments = (List<Attachment>) queryAttachmentsByEmailId
				.getResultList();
		return attachments;
	}
}
