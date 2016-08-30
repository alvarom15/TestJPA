package io.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import io.jpa.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	Contact findByName(String name);

}
