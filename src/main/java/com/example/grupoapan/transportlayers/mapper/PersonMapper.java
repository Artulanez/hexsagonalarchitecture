package com.example.grupoapan.transportlayers.mapper;

import com.example.grupoapan.entities.Person;
import com.example.grupoapan.transportlayers.openapi.model.PersonDetail;
import com.example.grupoapan.transportlayers.openapi.model.PersonInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDetail personDetailByPerson(Person person);

    Person personByPersonInput(PersonInput personInput);
}
