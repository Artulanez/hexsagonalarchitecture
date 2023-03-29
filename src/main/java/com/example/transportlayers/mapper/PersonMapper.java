package com.example.transportlayers.mapper;

import com.example.entities.Person;
import com.example.transportlayers.openapi.model.PersonDetail;
import com.example.transportlayers.openapi.model.PersonInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDetail personDetailByPerson(Person person);

    Person personByPersonInput(PersonInput personInput);
}
