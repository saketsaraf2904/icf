package com.icf.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "email")
@NamedQueries({ @NamedQuery(name = "findAllEmailByOutlookEmailId", query = "SELECT OBJECT(email) FROM Email email WHERE outlookEmailId=:outlookEmailId"),
	@NamedQuery(name = "findAllEmailIdsByUserName", query = "SELECT email.outlookEmailId FROM Email email WHERE fromUserName=:userName"),
	@NamedQuery(name = "findAllEmailsByUserNameForDownload", query = "SELECT OBJECT(email) FROM Email email WHERE userId=:userName and syncTypeID=0"),
	@NamedQuery(name = "findAllAttachmentsByEmailIdForDownload", query = "SELECT OBJECT(attachment) FROM Attachment attachment WHERE emailId=:emailId")})

public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	Integer outlookEmailId;
	String fromUserName;
	String subject;
	String body;
	String sender;
	String receiver;
	String cc;
	String bcc;
	Integer syncTypeId;
	String uniqueId;
	String created;
	String mainFolder;
	String subFolder;
	String userId;
	
	@Transient
	List<Attachment> attachments;
	
	public Email() {

	}

	public Email(Integer outlookEmailId, String fromUserName, String subject,
			String body, String from, String to, String cc, String bcc,
			Integer syncTypeId, String uniqueId, String created,
			String mainFolder, String subFolder, String userId) {
		this.outlookEmailId = outlookEmailId;
		this.fromUserName = fromUserName;
		this.subject = subject;
		this.body = body;
		this.sender = from;
		this.receiver = to;
		this.cc = cc;
		this.bcc = bcc;
		this.syncTypeId = syncTypeId;
		this.mainFolder = mainFolder;
		this.uniqueId = uniqueId;
		this.created = created;
		this.subFolder = subFolder;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getOutlookEmailId() {
		return outlookEmailId;
	}

	public void setOutlookEmailId(Integer outlookEmailId) {
		this.outlookEmailId = outlookEmailId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public Integer getSyncTypeId() {
		return syncTypeId;
	}

	public void setSyncTypeId(Integer syncTypeId) {
		this.syncTypeId = syncTypeId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getMainFolder() {
		return mainFolder;
	}

	public void setMainFolder(String mainFolder) {
		this.mainFolder = mainFolder;
	}

	public String getSubFolder() {
		return subFolder;
	}

	public void setSubFolder(String subFolder) {
		this.subFolder = subFolder;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}
