package org.example.contactproject;

import org.example.contactproject.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    void shouldReturnContacts() {
        assertFalse(contactService.findAll().isEmpty());
    }
}
