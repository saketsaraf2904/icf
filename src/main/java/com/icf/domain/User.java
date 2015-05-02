package com.icf.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="icf_user")
@NamedQueries({
    @NamedQuery(name="findAllUsersByUserName",
                query="SELECT OBJECT(user) FROM User user WHERE username=:userName and password=:password"),
    @NamedQuery(name="findAllSecurityQuestions",
    			query="SELECT OBJECT(usq) FROM UserSecurityQuestion usq"),
    @NamedQuery(name="findAccountStatusByStatusName",
    			query="SELECT OBJECT(uas) FROM UserAccountStatus uas WHERE status= :statusName"),
    @NamedQuery(name="findAllUsers",
    			query="SELECT OBJECT(u) FROM User u")    			
}) 
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", nullable=false)
	private Date creationDate;

	@Column(name="login_attempts_count", nullable=false)
	private int loginAttemptsCount;

	@Column(nullable=false)
	private String salt;
	
	@Column(nullable=false)
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name="security_answer", nullable=false)
	private String securityAnswer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updation_date", nullable=false)
	private Date updationDate;

	@Column(nullable=false, unique=true)
	private String username;

	//bi-directional many-to-one association to UserAccountStatus
	@ManyToOne
	@JoinColumn(name="account_status", nullable=false)
	private UserAccountStatus userAccountStatus;

	//bi-directional many-to-one association to UserSecurityQuestion
	@ManyToOne
	@JoinColumn(name="security_question_id", nullable=false)
	private UserSecurityQuestion userSecurityQuestion;
	
	
	private transient String mode;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getLoginAttemptsCount() {
		return this.loginAttemptsCount;
	}

	public void setLoginAttemptsCount(int loginAttemptsCount) {
		this.loginAttemptsCount = loginAttemptsCount;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSecurityAnswer() {
		return this.securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public Date getUpdationDate() {
		return this.updationDate;
	}

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserAccountStatus getUserAccountStatus() {
		return this.userAccountStatus;
	}

	public void setUserAccountStatus(UserAccountStatus userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}

	public UserSecurityQuestion getUserSecurityQuestion() {
		return this.userSecurityQuestion;
	}

	public void setUserSecurityQuestion(UserSecurityQuestion userSecurityQuestion) {
		this.userSecurityQuestion = userSecurityQuestion;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	@Override
	 public boolean equals(Object obj) {
		 if (this == obj) {
			 return true;
		 }
	 
		 if (obj == null || (obj.getClass() != this.getClass())) {
			 return false;
		 }
	 
		 User user = (User) obj;
		 if (username == user.username || (username != null && username.equals(user.username))) {
				 return true;
		 }
		 return false;
	 }
	 
	 @Override
	 public int hashCode() {
		 int hash = 7;
		 hash = (37 * hash) + (null == username ? 0 : username.hashCode());
		 return hash;
	 }
	 	
}