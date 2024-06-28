package com.example.contactsproject;

import com.example.contactsproject.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepo  extends JpaRepository<Contact, Integer>{
    Contact findById(int id);
}
