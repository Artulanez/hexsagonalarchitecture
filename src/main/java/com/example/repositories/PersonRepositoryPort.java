package com.example.repositories;

import com.example.entities.Person;

import java.util.List;

public interface PersonRepositoryPort {

    List<Person> getPerson();
    Person getPersonById(Long id);
    Person insert(Person person);
    void update(Long id, Person person);
    void delete(Long id);


}
