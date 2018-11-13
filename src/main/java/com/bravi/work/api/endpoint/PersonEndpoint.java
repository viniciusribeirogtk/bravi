package com.bravi.work.api.endpoint;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bravi.work.api.service.PersonService;
import com.bravi.work.document.Person;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/person")
public class PersonEndpoint {

	@Autowired
	private PersonService personService;
	
	@GetMapping(value="/v1/findById/{id}")
	@ApiOperation(value = "Find a Person by ID")
	public @ResponseBody ResponseEntity<Person> findById(@PathVariable("id")String id) {
		Person person = personService.findById(id);
		if (Objects.nonNull(person)) {
			return new ResponseEntity<>(person, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
    @PostMapping(value = "/v1/create")
    @ApiOperation(value = "Create a Person")
    public @ResponseBody ResponseEntity<Person> create(@RequestBody @Valid Person person) {
        Person personCreated = personService.create(person);
        return new ResponseEntity<>(personCreated, HttpStatus.OK);
    }
    
    @PutMapping(value = "/v1/update")
    @ApiOperation(value = "Update a Person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Person> update(@RequestBody @Valid Person person) {
    	Person personUpdated = personService.update(person);
    	return new ResponseEntity<>(personUpdated, HttpStatus.OK);
    }
    
    @DeleteMapping(value="/v1/delete")
    @ApiOperation(value = "Delete a Person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestBody @Valid Person person){
    	personService.delete(person);
    	return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }
}
