package com.example.entities;

import java.time.LocalDate;

public class PersonEntite {

    private Long id;

    private String name;

    private AddressEntite address;

    public PersonEntite(){

    }

    public PersonEntite(Long id, String name, LocalDate dateOfBirth, AddressEntite address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
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
