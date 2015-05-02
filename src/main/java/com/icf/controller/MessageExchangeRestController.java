package com.icf.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icf.dao.ContactDAOImpl;
import com.icf.dao.EmailDAOImpl;
import com.icf.dao.FolderDAOImpl;
import com.icf.dao.UserDAOImpl;
import com.icf.domain.Attachment;
import com.icf.domain.Contact;
import com.icf.domain.Email;
import com.icf.domain.Folder;
import com.icf.domain.Response;
import com.icf.domain.User;
import com.icf.exception.ContactAlreadyExistException;
import com.icf.exception.ContactsNotExistException;
import com.icf.exception.EmailAlreadyExistException;
import com.icf.exception.EmailNotExistException;
import com.icf.exception.FolderNotExistException;
import com.icf.exception.UserNotExistException;

@Controller
public class MessageExchangeRestController {

	/** Logger for logging. */
	private static final Logger LOGGER = Logger
			.getLogger(MessageExchangeRestController.class);
	private static final String FILE_PATH = "C:\\Attachment\\";

	/**
	 * Validate user.
	 * 
	 * @param userId
	 *            userId
	 * @param password
	 *            password
	 * @param domain
	 *            domain
	 * @return Map
	 */
	@RequestMapping(value = "/validateUser", method = RequestMethod.GET)
	public @ResponseBody
	Map validateUser(String userId, String password, String domain) {
		Map map = new HashMap();
		Response response = new Response();
		User user = new User();
		user.setUsername(userId);
		user.setPassword(password);

		try {
			UserDAOImpl userdaoimpl = new UserDAOImpl();
			user = userdaoimpl.validateUser(user);
			map.put("success", true);
			response.setDetails("");
			response.setCode("200");
			map.put("response", response);
		} catch (UserNotExistException e) {
			map.put("success", false);
			response.setDetails(e.getMessage());
			response.setCode("400");
			map.put("response", response);
		}

		return map;
	}

	/**
	 * Get directory structure
	 * 
	 * @param userId
	 *            userID
	 * @param password
	 *            password
	 * @param domain
	 *            domain
	 * @return Map
	 */
	@RequestMapping(value = "/getDirectoryStructure", method = RequestMethod.GET)
	public @ResponseBody
	Map getDirectoryStructure(String userId, String password, String domain) {
		Map validServiceResp = validateUser(userId, password, domain);
		Boolean success = (Boolean) validServiceResp.get("success");
		Response response = new Response();
		Map map = new HashMap();
		if (success) {
			try {
				List<Folder> folder = new ArrayList();
				FolderDAOImpl folderdaoimpl = new FolderDAOImpl();
				folder = folderdaoimpl.findFoldersByUserName(userId);
				map.put("success", true);
				response.setDetails("");
				response.setCode("200");
				map.put("response", response);
				map.put("folder", folder);
			} catch (FolderNotExistException e) {
				map.put("success", false);
				response.setDetails(e.getMessage());
				response.setCode("400");
				map.put("response", response);
			}
		} else {
			map = validServiceResp;
		}
		return map;
	}

	/**
	 * Upload Email with attachment
	 * 
	 * @param userId
	 *            userId
	 * @param password
	 *            password
	 * @param domain
	 *            domain
	 * @param outlookEmailId
	 *            outlookEmailId
	 * @param fromUserName
	 *            fromUserName
	 * @param subject
	 *            subject
	 * @param body
	 *            body
	 * @param from
	 *            from
	 * @param to
	 *            to
	 * @param cc
	 *            cc
	 * @param bcc
	 *            bcc
	 * @param syncTypeId
	 *            syncTypeId
	 * @param uniqueId
	 *            uniqueId
	 * @param created
	 *            created
	 * @param mainFolder
	 *            mainFolder
	 * @param subFolder
	 *            subFolder
	 * @param files
	 *            files
	 * @param attachmentCount
	 *            attachment
	 * @return Response response
	 */
	@RequestMapping(value = "/uploadEmail", method = RequestMethod.POST)
	public @ResponseBody
	Response uploadEmail(String userId, String password, String domain,
			Integer outlookEmailId, String fromUserName, String subject,
			String body, String from, String to, String cc, String bcc,
			Integer syncTypeId, String uniqueId, String created,
			String mainFolder, String subFolder,
			@RequestParam(value = "files") List<MultipartFile> files,
			Integer attachmentCount) {
		String strMsg = "";
		Response response = new Response();

		// Map validServiceResp = validateUser(userId, password, domain);
		// Boolean success = (Boolean) validServiceResp.get("success");
		Boolean success = true;
		Map map = new HashMap();
		if (success) {
			LOGGER.info(":Parameters Start:");
			LOGGER.info(outlookEmailId + "-" + fromUserName + "-" + subject
					+ "-" + body + "-" + from + "-");
			LOGGER.info(to + "-" + cc + "-" + bcc + "-" + syncTypeId + "-"
					+ uniqueId + "-" + created + "-" + mainFolder + "-"
					+ subFolder);
			LOGGER.info(":Parameters End:");
			Email email = new Email(outlookEmailId, fromUserName, subject,
					body, from, to, cc, bcc, syncTypeId, uniqueId, created,
					mainFolder, subFolder, userId);
			try {
				EmailDAOImpl emailDaoImpl = new EmailDAOImpl();
				List<Attachment> attachments = new ArrayList<Attachment>();
				if (files != null && attachmentCount > 0) {
					for (MultipartFile file : files) {
						Attachment attachment = new Attachment();
						String origfileName = file.getOriginalFilename();
						String fileName = origfileName.substring(0,
								origfileName.lastIndexOf("."));
						String extension = origfileName.substring(origfileName
								.lastIndexOf(".") + 1);
						attachment.setExtension(extension);
						attachment.setFileName(fileName);
						attachment.setPath(FILE_PATH
								+ email.getOutlookEmailId() + "\\" + fileName);

						// attachment.setData(file.getBytes());
						if (!upload(file.getOriginalFilename(), file,
								email.getOutlookEmailId())) {
							strMsg = strMsg + "Email " + subject
									+ " Attachment "
									+ file.getOriginalFilename()
									+ " failed to upload.\n";
						} else {
							strMsg = strMsg + "Email " + subject
									+ " Attachment "
									+ file.getOriginalFilename()
									+ " successfully uploaded.\n";
							attachments.add(attachment);
						}
					}
				}
				email = emailDaoImpl.createEmail(email, attachments);
				strMsg = strMsg + "Email added successfully.\n";
				map.put("success", true);
				response.setCode("200");
				map.put("response", response);
				map.put("email", email);
			} catch (EmailAlreadyExistException e) {
				map.put("success", false);
				strMsg = e.getMessage();
				response.setCode("400");
				map.put("response", response);
			} catch (Exception e) {
				map.put("success", false);
				strMsg = e.getMessage();
				response.setCode("400");
				map.put("response", response);
			}
		}

		response.setDetails(strMsg);
		return response;
	}

	/**
	 * Download Contacts.
	 * 
	 * @param userId
	 *            userId
	 * @param password
	 *            password
	 * @param domain
	 *            domain
	 * @return Map map
	 */
	@RequestMapping(value = "/downloadContacts", method = RequestMethod.GET)
	public @ResponseBody
	Map downloadContacts(String userId, String password, String domain) {
		Response response = new Response();
		Map validServiceResp = validateUser(userId, password, domain);
		Boolean success = (Boolean) validServiceResp.get("success");
		Map map = new HashMap();
		if (success) {
			try {
				List<Contact> contacts = null;
				ContactDAOImpl contactDaoImpl = new ContactDAOImpl();
				contacts = contactDaoImpl
						.findAllContactsByUserNameForDownload(userId);
				map.put("success", true);
				response.setDetails("");
				response.setCode("200");
				map.put("response", response);
				map.put("contacts", contacts);
			} catch (ContactsNotExistException e) {
				map.put("success", false);
				response.setDetails(e.getMessage());
				response.setCode("400");
				map.put("response", response);
			}
		} else {
			map = validServiceResp;
		}
		return map;
	}

	/**
	 * Upload Contact.
	 * 
	 * @param userId
	 *            userId
	 * @param password
	 *            password
	 * @param domain
	 *            domain
	 * @param contactId
	 *            contactId
	 * @param firstName
	 *            firstName
	 * @param lastName
	 *            lastName
	 * @param emailID
	 *            emailId
	 * @param emailID1
	 *            emailId1
	 * @param phone
	 *            phone
	 * @param phone1
	 *            phone1
	 * @param telephone
	 *            telephone
	 * @param telephone1
	 *            telephone1
	 * @param fax
	 *            fax
	 * @param fax1
	 *            fax1
	 * @param address1_1
	 *            address_1
	 * @param address1_2
	 *            address_2
	 * @param address1_3
	 *            address_3
	 * @param address1_city
	 *            address1_city
	 * @param address1_state
	 *            address1_state
	 * @param address1_country
	 *            address1_country
	 * @param address2_1
	 *            address2_1
	 * @param address2_2
	 *            address2_2
	 * @param address2_3
	 *            address2_3
	 * @param address2_city
	 *            address2_city
	 * @param address2_state
	 *            address2_state
	 * @param address2_country
	 *            address2_country
	 * @param syncTypeID
	 *            syncTypeId
	 * @return Map map
	 */
	@RequestMapping(value = "/uploadContact", method = RequestMethod.POST)
	public @ResponseBody
	Map uploadContact(String userId, String password, String domain,
			Integer contactId, String firstName, String lastName,
			String emailID, String emailID1, String phone, String phone1,
			String telephone, String telephone1, String fax, String fax1,
			String address1_1, String address1_2, String address1_3,
			String address1_city, String address1_state,
			String address1_country, String address2_1, String address2_2,
			String address2_3, String address2_city, String address2_state,
			String address2_country, Integer syncTypeID) {
		Response response = new Response();
		Map validServiceResp = validateUser(userId, password, domain);
		Boolean success = (Boolean) validServiceResp.get("success");
		Map map = new HashMap();
		if (success) {
			LOGGER.info(":Parameters Start:");
			LOGGER.info(contactId + "-" + userId + "-" + firstName + "-"
					+ lastName + "-" + emailID + "-" + emailID1);
			LOGGER.info(phone + "-" + phone1 + "-" + telephone + "-"
					+ telephone1 + "-" + fax + "-" + fax1);
			LOGGER.info(address1_1 + "-" + address1_2 + "-" + address1_3 + "-"
					+ address1_city + "-" + address1_state + "-"
					+ address1_country);
			LOGGER.info(address2_1 + "-" + address2_1 + "-" + address2_3 + "-"
					+ address2_city + "-" + address2_state + "-"
					+ address2_country + "-" + syncTypeID);

			Contact contact = new Contact(contactId, userId, firstName,
					lastName, emailID, emailID1, phone, phone1, telephone,
					telephone1, fax, fax1, address1_1, address1_2, address1_3,
					address1_city, address1_state, address1_country,
					address2_1, address2_1, address2_3, address2_city,
					address2_state, address2_country, syncTypeID);
			try {
				ContactDAOImpl contactDaoImpl = new ContactDAOImpl();
				contact = contactDaoImpl.uploadContact(contact);
				map.put("success", true);
				response.setDetails("Uploaded contacts.");
				response.setCode("200");
				map.put("response", response);
				map.put("contact", contact);
			} catch (ContactAlreadyExistException e) {
				map.put("success", false);
				response.setDetails(e.getMessage());
				response.setCode("400");
				map.put("response", response);
			} catch (Exception e) {
				map.put("success", false);
				response.setDetails(e.getMessage());
				response.setCode("400");
				map.put("response", response);
			}
		} else {
			map = validServiceResp;
		}
		return map;
	}

	/**
	 * Get Emails list.
	 * 
	 * @param userId
	 *            userId
	 * @param password
	 *            password
	 * @param domain
	 *            domain
	 * @return Map map
	 */
	@RequestMapping(value = "/getEmailList", method = RequestMethod.GET)
	public @ResponseBody
	Map getEmailList(String userId, String password, String domain) {
		Map validServiceResp = validateUser(userId, password, domain);
		Boolean success = (Boolean) validServiceResp.get("success");
		Response response = new Response();
		Map map = new HashMap();
		if (success) {
			try {
				List<Integer> emailIds = new ArrayList();
				EmailDAOImpl emaildaoimpl = new EmailDAOImpl();
				emailIds = emaildaoimpl.findEmailIdsByUserName(userId);
				map.put("success", true);
				response.setDetails("");
				response.setCode("200");
				map.put("response", response);
				map.put("emailIds", emailIds);
			} catch (EmailNotExistException e) {
				map.put("success", false);
				response.setDetails(e.getMessage());
				response.setCode("400");
				map.put("response", response);
			}
		} else {
			map = validServiceResp;
		}
		return map;
	}

	/**
	 * Download Emails.
	 * 
	 * @param userId
	 *            userId
	 * @param password
	 *            password
	 * @param domain
	 *            domain
	 * @return Map map
	 */
	@RequestMapping(value = "/downloadEmails", method = RequestMethod.GET)
	public @ResponseBody
	Map downloadEmails(String userId, String password, String domain) {
		Response response = new Response();
		Map validServiceResp = validateUser(userId, password, domain);
		Boolean success = (Boolean) validServiceResp.get("success");
		Map map = new HashMap();
		if (success) {
			try {
				List<Email> emails = null;
				EmailDAOImpl emailDaoImpl = new EmailDAOImpl();
				emails = emailDaoImpl
						.findAllEmailsByUserNameForDownload(userId);
				map.put("success", true);
				response.setDetails("");
				response.setCode("200");
				map.put("response", response);
				for (Email email : emails) {
					List<Attachment> attachments = emailDaoImpl
							.findAllAttachmentsByEmailIdForDownload(String
									.valueOf(email.getId()));
					for (Attachment attachment : attachments) {
						Path path = Paths.get(attachment.getPath() + "."
								+ attachment.getExtension());
						byte[] data = Files.readAllBytes(path);
						attachment.setFileData(data);
					}
					email.setAttachments(attachments);
				}
				map.put("emails", emails);
			} catch (EmailNotExistException e) {
				map.put("success", false);
				response.setDetails(e.getMessage());
				response.setCode("400");
				map.put("response", response);
			} catch (IOException io) {
				map.put("success", false);
				response.setDetails(io.getMessage());
				response.setCode("400");
				map.put("response", response);
			}
		} else {
			map = validServiceResp;
		}
		return map;
	}

	/**
	 * Upload multipart file.
	 * 
	 * @param name
	 *            name
	 * @param file
	 *            file
	 * @param outlookId
	 *            outlookId
	 * @return boolean boolean
	 */
	public boolean upload(String name, MultipartFile file, Integer outlookId) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(FILE_PATH + outlookId.toString());

				if (!dir.exists()) {
					System.out.println("creating directory: " + dir);
					boolean result = dir.mkdir();

					if (result) {
						System.out.println("DIR created");
					}
				}

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(FILE_PATH
								+ outlookId.toString() + "\\" + name)));
				stream.write(bytes);
				stream.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

}
