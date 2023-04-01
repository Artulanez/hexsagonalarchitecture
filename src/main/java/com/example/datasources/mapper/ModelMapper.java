package com.example.datasources.mapper;

import com.example.datasources.model.Person;
import com.example.entities.PersonEntite;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    Person personToPersonEntite(PersonEntite personEntite);

    PersonEntite personEntiteToPerson(Person person);
}
