package com.example.datasources.service;

import com.example.entities.PersonEntite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntite, Long> {
}
