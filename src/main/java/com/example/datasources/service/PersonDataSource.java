package com.example.datasources.service;

import com.example.datasources.services.ViaCepService;
import com.example.entities.AddressEntite;
import com.example.entities.PersonEntite;

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
    public List<PersonEntite> getPerson() {
        List<PersonEntite> list =  personRepository.findAll();
        return  list;
    }

    @Override
    public PersonEntite getPersonById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public PersonEntite insert(PersonEntite personEntite) {
        personEntite.setAddress(getAddress(personEntite.getAddress().getCep()));
        personRepository.save(personEntite);
        return personEntite;
    }

    @Override
    public void update(Long id, PersonEntite personEntite) {
        PersonEntite personEntiteBase = personRepository.findById(id).get();
        personEntiteBase.setName(personEntite.getName());
        personRepository.save(personEntiteBase);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }


    private AddressEntite getAddress(String cep){
        return viaCepService.getAddressByCep(cep);
    }
}
