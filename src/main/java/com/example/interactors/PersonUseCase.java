package com.example.interactors;

import com.example.entities.PersonEntite;

import com.example.repositories.PersonRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;

    public PersonUseCase(PersonRepositoryPort personRepository) {
        this.personRepositoryPort = personRepository;
    }

    public List<PersonEntite> getPerson() {
        return personRepositoryPort.getPerson();
    }

    public PersonEntite getPersonById(Long id){
        return personRepositoryPort.getPersonById(id);
    }

    public PersonEntite postPerson(PersonEntite personEntite){
       return personRepositoryPort.insert(personEntite);
    }

    public void removePerson(Long id){
        personRepositoryPort.delete(id);
    }

    public void updatePerson(Long id, PersonEntite personEntite){
        personRepositoryPort.update(id, personEntite);
    }

}
