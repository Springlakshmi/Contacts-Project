package com.example.contactsproject;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api")
public class ContactsController {
    @Autowired
    private ContactsRepo contactsRepo;

    @PostMapping("/create")
    public ResponseEntity<Contacts> createConatcts(@RequestBody Contacts contact) {
        log.info("contact created successfully:" + contact);
        return new ResponseEntity<>(contactsRepo.save(contact), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Contacts> updateContactBYID(@PathVariable int id,@RequestBody Contacts contacts){
        log.info("contact updated successfully by {}:"+id);
        Optional<Contacts> contact=contactsRepo.findById(id);
        if (contact.isPresent()) {
            contact.get().setId((contacts.getId()));

            contact.get().setFirstName(contacts.getFirstName());
            contact.get().setLastName(contacts.getLastName());
            contact.get().setAddress(contacts.getAddress());
            contact.get().setPhoneNumber(contacts.getPhoneNumber());
            contact.get().setGmail(contacts.getGmail());
          return new ResponseEntity<>(contactsRepo.save(contact.get()),HttpStatus.OK);
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
