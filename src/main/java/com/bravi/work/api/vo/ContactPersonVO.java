package com.bravi.work.api.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.bravi.work.document.Contact;

public class ContactPersonVO {

	@NotEmpty
	private String personId;
	@NotNull
	private List<Contact> contacts;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String id) {
		this.personId = id;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
