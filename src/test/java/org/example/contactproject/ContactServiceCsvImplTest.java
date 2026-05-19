package org.example.contactproject;

import org.example.contactproject.service.ContactServiceCsvImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ContactServiceCsvImplTest {

    private ContactServiceCsvImpl service;

    @BeforeEach
    void setUp() {
        service = new ContactServiceCsvImpl();
    }

    @Test
    void shouldReturnEmptyWhenNoContacts() {
        List<Contact> contacts = service.findAll();
        assertTrue(contacts.isEmpty());
    }

    @Test
    void shouldReturnNullWhenNotFound() {
        Contact contact = service.findById(999L);
        assertNull(contact);
    }

    @Test
    void findAll() {
        service.create(new Contact(null, "Ivanov", "Ivan", "I", "123"));
        service.create(new Contact(null, "Petrov", "Petr", "P", "456"));

        List<Contact> contacts = service.findAll();

        assertNotNull(contacts);
        assertEquals(2, contacts.size());
    }

    @Test
    void findById() {
        Contact created = service.create(
                new Contact(null, "Ivanov", "Ivan", "I", "123")
        );

        Contact found = service.findById(created.getId());

        assertNotNull(found);
        assertEquals("Ivan", found.getFirstName());
    }

    @Test
    void create() {
        Contact contact = new Contact(null, "User", "Test", "T", "999");

        Contact created = service.create(contact);

        assertNotNull(created.getId());
        assertEquals("Test", created.getFirstName());
        assertEquals("User", created.getLastName());
    }

    @Test
    void update() {
        Contact created = service.create(
                new Contact(null, "OldLast", "OldName", "M", "123")
        );

        Contact updated = new Contact(null, "NewLast", "Updated", "M", "999");

        service.update(created.getId(), updated);

        Contact result = service.findById(created.getId());

        assertEquals("Updated", result.getFirstName());
        assertEquals("NewLast", result.getLastName());
    }

    @Test
    void delete() {
        Contact created = service.create(
                new Contact(null, "Ivanov", "Ivan", "I", "123")
        );

        service.delete(created.getId());

        Contact result = service.findById(created.getId());

        assertNull(result);
    }
}