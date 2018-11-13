package com.bravi.work.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bravi.work.document.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

}
