package com.bravi.work.api.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bravi.work.api.repository.ContactRepository;
import com.bravi.work.api.repository.PersonRepository;
import com.bravi.work.api.vo.ContactPersonVO;
import com.bravi.work.document.Contact;
import com.bravi.work.document.Person;

@Service
public class ContactListService {
	
	private static final Logger log = LoggerFactory.getLogger(ContactListService.class);
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private PersonRepository personRepository;

	public Person addContactToPerson(ContactPersonVO contactPersonVO) {
		validateContactList(contactPersonVO.getContacts());
		Person person = personRepository.findOne(contactPersonVO.getPersonId());
		if (Objects.isNull(person)) {
			log.error("Person not found: "+person.getId());
			throw new RuntimeException("Person not found: "+person.getId());
		}
		if (Objects.isNull(person.getContacts()) || person.getContacts().isEmpty()) {
			person.setContacts(contactPersonVO.getContacts());
		} else {
			person.getContacts().addAll(contactPersonVO.getContacts());
		}
		return personRepository.save(person);
	}


	public Contact findById(String id) {
		return contactRepository.findOne(id);
	}

	public Person update(Person person) {
		Person personToUpdate = findPersonToUpdate(person);
		personToUpdate.setContacts(person.getContacts());
		return personRepository.save(personToUpdate);
	}


	private Person findPersonToUpdate(Person person) {
		Person personToUpdate = personRepository.findOne(person.getId());
		if (Objects.isNull(person)) {
			log.error("Person not found: "+person.getId());
			throw new RuntimeException("Person not found: "+person.getId());
		}
		return personToUpdate;
	}

	private void validateContactList(List<Contact> contacts) {
		if (Objects.isNull(contacts)
				|| contacts.isEmpty()) {
			log.error("ContactList must not be null");
			throw new RuntimeException("ContactList must not be null");
		}
	}
}
