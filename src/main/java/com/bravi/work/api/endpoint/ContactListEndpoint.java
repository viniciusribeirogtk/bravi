package com.bravi.work.api.endpoint;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bravi.work.api.service.ContactListService;
import com.bravi.work.api.vo.ContactPersonVO;
import com.bravi.work.document.Person;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contact-list")
public class ContactListEndpoint {

	private static final Logger log = LoggerFactory.getLogger(ContactListEndpoint.class);
	
	@Autowired
	private ContactListService contactListService;
	
    @PostMapping(value = "/v1/contacts/add")
    @ApiOperation(value = "Create a contact")
    public @ResponseBody ResponseEntity<Person> getByNameAndId(@RequestBody @Valid ContactPersonVO contactPersonVO) {
        log.info("\nAdd one or many contacts to a person");
        Person personContacts = contactListService.addContactToPerson(contactPersonVO);
        return new ResponseEntity<>(personContacts, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/update")
    @ApiOperation(value = "Update a Contact", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Person> update(@RequestBody @Valid Person person) {
    	Person personUpdated = contactListService.update(person);
    	return new ResponseEntity<>(personUpdated, HttpStatus.OK);
    }
}
