package com.example.transportlayers.impl;

import com.example.entities.PersonEntite;
import com.example.entities.AddressEntite;
import com.example.grupoapan.transportlayers.openapi.api.PersonApi;
import com.example.grupoapan.transportlayers.openapi.model.PersonDetail;
import com.example.grupoapan.transportlayers.openapi.model.PersonInput;
import com.example.interactors.PersonUseCase;
import com.example.transportlayers.mapper.PersonMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonApiImpl implements PersonApi{

    private final PersonUseCase personUseCase;

    public PersonApiImpl(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PersonDetail>> getPerson(){
        var list = personUseCase.getPerson();
        List<PersonDetail> resultList = new ArrayList<PersonDetail>();
        list.forEach( person -> {
            PersonDetail personDetail = PersonMapper.INSTANCE.personDetailByPerson(person);
            loadAddressDetail(person, personDetail);
            resultList.add(personDetail);
        });

        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @Override
    @GetMapping("/{personId}")
    public ResponseEntity<PersonDetail> getPersonById(@PathVariable("personId")  Long personId){
        var person = personUseCase.getPersonById(personId);
        PersonDetail personDetail = PersonMapper.INSTANCE.personDetailByPerson(person);
        loadAddressDetail(person, personDetail);

        return ResponseEntity.status(HttpStatus.OK).body(personDetail);
    }

    @Override
    @PostMapping
    public ResponseEntity<PersonDetail> postPerson( PersonInput personInput){
        var person = PersonMapper.INSTANCE.personByPersonInput(personInput);
        person.setAddress(new AddressEntite());
        person.getAddress().setCep(personInput.getCep());
        person = personUseCase.postPerson(person);
        PersonDetail personDetail = PersonMapper.INSTANCE.personDetailByPerson(person);
        loadAddressDetail(person, personDetail);

        return ResponseEntity.status(HttpStatus.OK).body(personDetail);
    }


    private static void loadAddressDetail(PersonEntite personEntite, PersonDetail personDetail) {
        personDetail.setCep(personEntite.getAddress().getCep());
        personDetail.setBairro(personEntite.getAddress().getBairro());
        personDetail.setLocalidade(personEntite.getAddress().getLocalidade());
        personDetail.setComplemento(personEntite.getAddress().getComplemento());
        personDetail.setLogradouro(personEntite.getAddress().getLogradouro());
        personDetail.setUf(personEntite.getAddress().getUf());
    }

    @Override
    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> removePerson(@PathVariable("personId")  Long personId) {
        personUseCase.removePerson(personId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @PutMapping("/{personId}")
    public ResponseEntity<PersonDetail> updatePerson(@PathVariable("personId")  Long personId, @RequestBody PersonInput personInput){
        personUseCase.updatePerson(personId, PersonMapper.INSTANCE.personByPersonInput(personInput));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
