package com.example.datasources.model;

import com.example.entities.AddressEntite;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="TB_PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private AddressEntite address;

    public Person(){

    }

    public Person(Long id, String name, LocalDate dateOfBirth, AddressEntite address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressEntite getAddress() {
        return address;
    }

    public void setAddress(AddressEntite address) {
        this.address = address;
    }
}
