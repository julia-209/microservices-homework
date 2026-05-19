package org.example.contactproject.service;

import org.example.contactproject.Contact;
import org.example.contactproject.TransactionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service
@Transactional
public class ContactServiceCsvImpl implements ContactService {

    @Value("classpath:contacts.csv")
    private Resource resource;
    private final Map<Long, Contact> storage = new HashMap<>();
    private Long idCounter = 1L;


    @PostConstruct
    public void init() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))) {

            String line;
            boolean isFirst = true;

            while ((line = reader.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                String[] parts = line.split(",");

                Contact contact = new Contact(
                        idCounter,
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3]
                );

                storage.put(idCounter, contact);
                idCounter++;
            }

        } catch (Exception e) {
            throw new TransactionException("Ошибка загрузки CSV", e);
        }
    }


    @Override
    @Transactional(readOnly = true)
    @Cacheable("contacts")
    public List<Contact> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "contact", key = "#id")
    public Contact findById(Long id) {
        return storage.get(id);
    }

    @Override
    @CacheEvict(value = {"contacts", "contact"}, allEntries = true)
    public Contact create(Contact contact) {
        try {
        contact.setId(idCounter++);
        storage.put(contact.getId(), contact);
        return contact;
        } catch (Exception e) {
            throw new TransactionException("Ошибка создания контакта", e);
        }
    }
    @Override
    @CacheEvict(value = {"contacts", "contact"}, allEntries = true)
    public Contact update(Long id, Contact contact) {
        try {
        contact.setId(id);
        storage.put(id, contact);
        return contact;
        } catch (Exception e) {
            throw new TransactionException("Ошибка создания контакта", e);
        }
    }

    @Override
    @CacheEvict(value = {"contacts", "contact"}, allEntries = true)
    public void delete(Long id) {
        try {
            if (!storage.containsKey(id)) {
            throw new TransactionException("Контакт не найден");
        }
            storage.remove(id);
        } catch (Exception e) {
            throw new TransactionException("Ошибка создания контакта", e);
        }
    }
}