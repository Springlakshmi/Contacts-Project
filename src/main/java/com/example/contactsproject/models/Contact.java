package com.example.contactsproject.models;
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
public class Contact {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String address;

}
