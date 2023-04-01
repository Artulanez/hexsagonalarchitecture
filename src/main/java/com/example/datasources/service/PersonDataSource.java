package com.example.datasources.service;

import com.example.datasources.mapper.ModelMapper;
import com.example.datasources.model.Person;
import com.example.datasources.services.ViaCepService;
import com.example.entities.AddressEntite;
import com.example.entities.PersonEntite;

import com.example.repositories.PersonRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDataSource  implements PersonRepositoryPort {

    private final PersonRepository personRepository;
    private final ViaCepService viaCepService;

    public PersonDataSource(PersonRepository personRepository, ViaCepService viaCepService) {
        this.personRepository = personRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public List<PersonEntite> getPerson() {
        List<Person> list =  personRepository.findAll();
        return  list.stream().map(item -> ModelMapper.INSTANCE.personEntiteToPerson(item)).collect(Collectors.toList());
    }

    @Override
    public PersonEntite getPersonById(Long id) {
        return ModelMapper.INSTANCE.personEntiteToPerson(personRepository.findById(id).get());
    }

    @Override
    public PersonEntite insert(PersonEntite personEntite) {
        personEntite.setAddress(getAddress(personEntite.getAddress().getCep()));
        Person person = ModelMapper.INSTANCE.personToPersonEntite(personEntite);
        personRepository.save(person);
        return personEntite;
    }

    @Override
    public void update(Long id, PersonEntite personEntite) {
        Person personBase = personRepository.findById(id).get();
        personBase.setName(personEntite.getName());
        personRepository.save(personBase);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }


    private AddressEntite getAddress(String cep){
        return viaCepService.getAddressByCep(cep);
    }
}
