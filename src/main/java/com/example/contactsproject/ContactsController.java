package com.example.contactsproject;


import com.example.contactsproject.models.Contact;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
public class ContactsController {

    private final ContactsRepo contactsRepo;

    @PostMapping("/create")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        log.info("creating contact: {}", contact);
        Contact savedContact = contactsRepo.save(contact);
        log.info("contact created successfully: {}", contact);
        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContactBYID(@PathVariable int id,@RequestBody Contact contact){
        log.info("updating contact by {}:", id);
        Contact existingContact =contactsRepo.findById(id);
        if (!ObjectUtils.isEmpty(existingContact)) {
            existingContact.setFirstName(contact.getFirstName());
            existingContact.setLastName(contact.getLastName());
            existingContact.setAddress(contact.getAddress());
            existingContact.setPhoneNumber(contact.getPhoneNumber());
            existingContact.setEmail(contact.getEmail());
            Contact updatedContact = contactsRepo.save(existingContact);
            log.info("contact updated successfully by {}:", id);
          return new ResponseEntity<>(updatedContact,HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contacts> deleteContactById(@PathVariable int id) {
        log.info("contact deleted successfully:" + id);
        Optional<Contacts> contact = contactsRepo.findById(id);


        if (contact.isPresent()) {

            contactsRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/read")
    public ResponseEntity<List<Contacts>> readContacts(){
        log.info("getting the data from contacts list:"   );
        return new ResponseEntity<>(contactsRepo.findAll(),HttpStatus.OK);

    }
    @GetMapping("/contact/{id}")
    public ResponseEntity<Contacts> getContactByID(@PathVariable Integer id){
        Optional<Contacts> contact=contactsRepo.findById(id);

        if(contact.isPresent()){
            log.info("getting contact with id: {}", id);
            return new ResponseEntity<>(contact.get(),HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
