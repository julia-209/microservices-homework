package org.example.contactproject.service;

import org.example.contactproject.Contact;

import java.util.List;
public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact create(Contact contact);
    Contact update(Long id, Contact contact);
    void delete(Long id);
}
