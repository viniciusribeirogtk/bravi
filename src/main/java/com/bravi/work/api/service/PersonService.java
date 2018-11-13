package com.bravi.work.api.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bravi.work.api.repository.PersonRepository;
import com.bravi.work.document.Person;

@Service
public class PersonService {
	
	private static final Logger log = LoggerFactory.getLogger(PersonService.class);

	@Autowired
	private PersonRepository personRepository;
	
	public Person create(Person person) {
		person.setId(null);
		return personRepository.save(person);
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public Person update(Person person) {
		log.info("Updating Person");
		validatePersonId(person);
		Person personToUpdate = personRepository.findOne(person.getId());
		Person personUpdated = null;
		if (Objects.nonNull(personToUpdate)) {
			personUpdated = personRepository.save(person);
		} else {
			log.error("Person: "+person.getId()+ " not found");
			throw new RuntimeException("Person: "+person.getId()+ " not found");
			
		}
		log.info("Person Updated!");
		return personUpdated;
	}

	public void delete(Person person) {
		log.info("Deleting Person: " +person.getId());
		validatePersonId(person);
		Person personToDelete = findPersonById(person);
		personRepository.delete(personToDelete);
		log.info("Person Deleted!");
	}

	private Person findPersonById(Person person) {
		log.info("Finding Person by Id" +person.getId());
		Person personToDelete = personRepository.findOne(person.getId());
		if (Objects.isNull(personToDelete)) {
			log.error("Person: "+person.getId()+ " not found");
			throw new RuntimeException("Person: "+person.getId()+ " not found");
		}
		log.info("Person Founded!");
		return personToDelete;
	}

	private void validatePersonId(Person person) {
		if (Objects.isNull(person.getId()) ||
				person.getId().isEmpty()) {
			log.error("You must Pass an Person Id to update");
			throw new RuntimeException("You must Pass an Person Id to update");
		}
	}

	public Person findById(String id) {
		log.info("Finding Person by Id" +id);
		return personRepository.findOne(id);
	}

}
