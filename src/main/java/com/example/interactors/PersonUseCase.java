package com.example.interactors;

import com.example.entities.Person;

import com.example.repositories.PersonRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;

    public PersonUseCase(PersonRepositoryPort personRepository) {
        this.personRepositoryPort = personRepository;
    }

    public List<Person> getPerson() {
        return personRepositoryPort.getPerson();
    }

    public Person getPersonById(Long id){
        return personRepositoryPort.getPersonById(id);
    }

    public Person postPerson(Person person){
       return personRepositoryPort.insert(person);
    }

    public void removePerson(Long id){
        personRepositoryPort.delete(id);
    }

    public void updatePerson(Long id, Person person){
        personRepositoryPort.update(id, person);
    }

}
