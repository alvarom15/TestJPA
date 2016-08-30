package io.jpa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import io.jpa.domain.Contact;
import io.jpa.repository.ContactRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dataAccessContext-test.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TestContactRepositoryIT {
	
	private ContactRepository contactRepository;
	
	@Autowired
	public void setContactRepository(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	@Test
	@Rollback(value = false)
	public void should_insert_contact() {
		System.out.println("TESTING");
		long countOrig = contactRepository.count();
		Contact contact = createContact("Alvaro");
		Contact contact2 = createContact("Antonio");
		Contact contact3 = createContact("Angel");
		Contact contact4 = createContact("Patricia");
		
		contactRepository.save(contact);
		contactRepository.save(contact2);
		contactRepository.save(contact3);
		contactRepository.save(contact4);
		
		long count = contactRepository.count();
		Assert.assertEquals(4, count-countOrig);
	}
	
	
	private Contact createContact(String name) {
		Contact contact = new Contact();
		contact.setName(name);
		return contact;
	}
}
