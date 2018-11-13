package com.bravi.work.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bravi.work.document.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
