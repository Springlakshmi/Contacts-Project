package com.example.contactsproject.service;

import com.example.contactsproject.models.Contact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface ContactService {
    ResponseEntity<Contact> createContact(@RequestBody Contact contact);

  ResponseEntity<Contact> updateContactBYId(@PathVariable int id,
                                                     @RequestBody Contact contact);
   ResponseEntity<Contact> deleteContactById(@PathVariable int id);

    ResponseEntity<List<Contact>> readContacts();
     ResponseEntity<Contact> getContactByID(@PathVariable Integer id);




}