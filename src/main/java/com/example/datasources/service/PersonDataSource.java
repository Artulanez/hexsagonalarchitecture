package com.example.datasources.service;

import com.example.datasources.services.ViaCepService;
import com.example.entities.Address;
import com.example.entities.Person;

import com.example.repositories.PersonRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonDataSource  implements PersonRepositoryPort {

    private final PersonRepository personRepository;
    private final ViaCepService viaCepService;

    public PersonDataSource(PersonRepository personRepository, ViaCepService viaCepService) {
        this.personRepository = personRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public List<Person> getPerson() {
        List<Person> list =  personRepository.findAll();
        return  list;
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person insert(Person person) {
        person.setAddress(getAddress(person.getAddress().getCep()));
        personRepository.save(person);
        return person;
    }

    @Override
    public void update(Long id, Person person) {
        Person personBase = personRepository.findById(id).get();
        personBase.setName(person.getName());
        personRepository.save(personBase);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }


    private Address getAddress(String cep){
        return viaCepService.getAddressByCep(cep);
    }
}
