package com.example.contactsproject.controller;
import com.example.contactsproject.models.Contact;
import com.example.contactsproject.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Slf4j

@RequiredArgsConstructor


@RequestMapping("/api/v1/contacts")
public class ContactsController {
    @Autowired

    private  final  ContactService contactService;


    @PostMapping("/create")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContactBYId(@PathVariable int id,
                                                     @RequestBody Contact contact){

        return contactService.updateContactBYId(id,contact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contact> deleteContactById(@PathVariable int id) {
        return contactService.deleteContactById(id);

    }
    @GetMapping("/read")
    public ResponseEntity<List<Contact>> readContacts(){
        return contactService.readContacts();
    }
    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContactByID(@PathVariable Integer id) {
        return contactService.getContactByID(id);

    }




}
