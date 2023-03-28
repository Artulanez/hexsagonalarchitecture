package com.example.grupoapan.datasources.database;

import com.example.grupoapan.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
