package com.icf.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user_security_question database table.
 * 
 */
@Entity
@Table(name="user_security_question")
public class UserSecurityQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(nullable=false, unique=true)
	private String question;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userSecurityQuestion")
	private List<User> users;

	public UserSecurityQuestion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}