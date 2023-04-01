package com.example.repositories;

import com.example.entities.PersonEntite;

import java.util.List;

public interface PersonRepositoryPort {

    List<PersonEntite> getPerson();
    PersonEntite getPersonById(Long id);
    PersonEntite insert(PersonEntite personEntite);
    void update(Long id, PersonEntite personEntite);
    void delete(Long id);


}
