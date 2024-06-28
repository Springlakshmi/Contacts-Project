package com.example.contactsproject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="contacts")
@AllArgsConstructor
@NoArgsConstructor
public class Contacts {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String gmail;
    private long phoneNumber;
    private String address;



}
