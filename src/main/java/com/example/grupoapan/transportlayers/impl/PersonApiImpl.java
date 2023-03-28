package com.example.grupoapan.transportlayers.impl;

import com.example.grupoapan.interactors.PersonUseCase;
import com.example.grupoapan.transportlayers.mapper.PersonMapper;
import com.example.grupoapan.transportlayers.openapi.api.PersonApi;
import com.example.grupoapan.transportlayers.openapi.model.PersonDetail;
import com.example.grupoapan.transportlayers.openapi.model.PersonInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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
        var resultList = list
                .stream()
                .map(item -> PersonMapper.INSTANCE.personDetailByPerson(item))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @Override
    @GetMapping("/{personId}")
    public ResponseEntity<PersonDetail> getPersonById(@PathVariable("personId")  Long personId){
        var person = personUseCase.getPersonById(personId);
        PersonDetail personDetail = PersonMapper.INSTANCE.personDetailByPerson(person);
        return ResponseEntity.status(HttpStatus.OK).body(personDetail);
    }

    @Override
    @PostMapping
    public ResponseEntity<PersonDetail> postPerson( PersonInput personInput){
        var person = PersonMapper.INSTANCE.personByPersonInput(personInput);
        person.getAddress().setCep(personInput.getCep());
        person = personUseCase.postPerson(PersonMapper.INSTANCE.personByPersonInput(personInput));
        PersonDetail personDetail = PersonMapper.INSTANCE.personDetailByPerson(person);
        return ResponseEntity.status(HttpStatus.OK).body(personDetail);
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
