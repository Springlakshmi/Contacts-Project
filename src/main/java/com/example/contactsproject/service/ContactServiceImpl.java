package com.example.contactsproject.service;
import com.example.contactsproject.models.Contact;
import com.example.contactsproject.repo.ContactsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service


public class ContactServiceImpl implements ContactService {
    @Autowired
     private final ContactsRepo contactsRepo;

    @Override
    public ResponseEntity<Contact> createContact(Contact contact) {
        log.info("creating contact: {}", contact);
        Contact savedContact = contactsRepo.save(contact);
        log.info("contact created successfully: {}", contact);
        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);

    }


    @Override

    public ResponseEntity<Contact> updateContactBYId(int id, Contact contact) {
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
    @Override
    public ResponseEntity<Contact> deleteContactById(int id){
        log.info("deleting contact by {}:", id);
        Contact existingContact =contactsRepo.findById(id);
        if (!ObjectUtils.isEmpty(existingContact)) {

            contactsRepo.deleteById(id);
            log.info("contact deleted successfully by {}:", id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<List<Contact>> readContacts(){
        log.info("getting the data from contacts list:"   );
        return new ResponseEntity<>(contactsRepo.findAll(),HttpStatus.OK);

    }
    public ResponseEntity<Contact> getContactByID(Integer id){

        Optional<Contact> contact=contactsRepo.findById(id);

        if(contact.isPresent()){
            log.info("getting contact with id: {}", id);
            return new ResponseEntity<>(contact.get(),HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}



