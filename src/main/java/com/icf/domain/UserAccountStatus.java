package com.icf.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user_account_status database table.
 * 
 */
@Entity
@Table(name="user_account_status")
public class UserAccountStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(nullable=false, unique=true)
	private String status;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userAccountStatus")
	private List<User> users;

	public UserAccountStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}