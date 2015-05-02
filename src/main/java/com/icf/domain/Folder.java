package com.icf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="folder")
@NamedQueries({
    @NamedQuery(name="findAllFoldersByUserName",
                query="SELECT OBJECT(folder) FROM Folder folder WHERE username=:userName")
}) 
public class Folder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="foldername", nullable=true)
	String mainFolder;
	@Column(name="parent", nullable=false)
	String subFolder;
	@Column(name="username", nullable=true)
	String userName;

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
