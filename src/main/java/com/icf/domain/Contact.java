package com.icf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
@NamedQueries({
		@NamedQuery(name = "findAllContactsByUserNameForDownload", query = "SELECT OBJECT(contact) FROM Contact contact WHERE userId=:userName and syncTypeID=0"),
		@NamedQuery(name = "findAllContactsByUserNameAndContactId", query = "SELECT OBJECT(contact) FROM Contact contact WHERE userId=:userName and emailId=:emailId"),
		@NamedQuery(name = "updateSyncTypeIdForContact", query = "UPDATE Contact SET syncTypeID=1 WHERE id=:contactId")
		
})
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	Integer contactId;
	String userId;
	String firstName;
	String lastName;
	String emailID;
	String emailID1;
	String phone;
	String phone1;
	String telephone;
	String telephone1;
	String fax;
	String fax1;
	String address1_1;
	String address1_2;
	String address1_3;
	String address1_city;
	String address1_state;
	String address1_country;
	String address2_1;
	String address2_2;
	String address2_3;
	String address2_city;
	String address2_state;
	String address2_country;
	Integer syncTypeID;

	public Contact() {
		
	}
	
	
	public Contact(Integer contactId, String userId, String firstName,
			String lastName, String emailID, String emailID1, String phone,
			String phone1, String telephone, String telephone1, String fax,
			String fax1, String address1_1, String address1_2,
			String address1_3, String address1_city, String address1_state,
			String address1_country, String address2_1, String address2_2,
			String address2_3, String address2_city, String address2_state,
			String address2_country, Integer syncTypeID) {
		this.contactId = contactId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.emailID1 = emailID1;
		this.phone = phone;
		this.phone1 = phone1;
		this.telephone = telephone;
		this.telephone1 = telephone1;
		this.fax = fax;
		this.fax1 = fax1;
		this.address1_1 = address1_1;
		this.address1_2 = address1_2;
		this.address1_3 = address1_3;
		this.address1_city = address1_city;
		this.address1_state = address1_state;
		this.address1_country = address1_country;
		this.address2_1 = address2_1;
		this.address2_2 = address2_2;
		this.address2_3 = address2_3;
		this.address2_city = address2_city;
		this.address2_state = address2_state;
		this.address2_country = address2_country;
		this.syncTypeID = syncTypeID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getEmailID1() {
		return emailID1;
	}

	public void setEmailID1(String emailID1) {
		this.emailID1 = emailID1;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax1() {
		return fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	public String getAddress1_1() {
		return address1_1;
	}

	public void setAddress1_1(String address1_1) {
		this.address1_1 = address1_1;
	}

	public String getAddress1_2() {
		return address1_2;
	}

	public void setAddress1_2(String address1_2) {
		this.address1_2 = address1_2;
	}

	public String getAddress1_3() {
		return address1_3;
	}

	public void setAddress1_3(String address1_3) {
		this.address1_3 = address1_3;
	}

	public String getAddress1_city() {
		return address1_city;
	}

	public void setAddress1_city(String address1_city) {
		this.address1_city = address1_city;
	}

	public String getAddress1_state() {
		return address1_state;
	}

	public void setAddress1_state(String address1_state) {
		this.address1_state = address1_state;
	}

	public String getAddress1_country() {
		return address1_country;
	}

	public void setAddress1_country(String address1_country) {
		this.address1_country = address1_country;
	}

	public String getAddress2_1() {
		return address2_1;
	}

	public void setAddress2_1(String address2_1) {
		this.address2_1 = address2_1;
	}

	public String getAddress2_2() {
		return address2_2;
	}

	public void setAddress2_2(String address2_2) {
		this.address2_2 = address2_2;
	}

	public String getAddress2_3() {
		return address2_3;
	}

	public void setAddress2_3(String address2_3) {
		this.address2_3 = address2_3;
	}

	public String getAddress2_city() {
		return address2_city;
	}

	public void setAddress2_city(String address2_city) {
		this.address2_city = address2_city;
	}

	public String getAddress2_state() {
		return address2_state;
	}

	public void setAddress2_state(String address2_state) {
		this.address2_state = address2_state;
	}

	public String getAddress2_country() {
		return address2_country;
	}

	public void setAddress2_country(String address2_country) {
		this.address2_country = address2_country;
	}

	public Integer getSyncTypeID() {
		return syncTypeID;
	}

	public void setSyncTypeID(Integer syncTypeID) {
		this.syncTypeID = syncTypeID;
	}
}
