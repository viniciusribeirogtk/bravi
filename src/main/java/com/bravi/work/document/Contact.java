package com.bravi.work.document;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.bravi.work.enums.ContactTypeEnum;

@Document(collection = "contact")
public class Contact {

	private Integer number;
	private String mail;
	@NotNull
	private ContactTypeEnum contactType;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ContactTypeEnum getContactType() {
		return contactType;
	}

	public void setContactType(ContactTypeEnum contactType) {
		this.contactType = contactType;
	}

}
