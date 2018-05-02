package com.example;

import java.io.Serializable;

public class ExternalDownloadModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String externalId;
	private String filePath;
	private boolean agree;
	private String formCreateTime;
	private String lastUpdateTime;
	private String firstName;
	private String lastName;
	private String email;
	private String emailFrom;

	public ExternalDownloadModel() {
		
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public String getFormCreateTime() {
		return formCreateTime;
	}

	public void setFormCreateTime(String formCreateTime) {
		this.formCreateTime = formCreateTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
