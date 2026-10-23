package com.example.Museum.repositories;

import com.example.Museum.entities.Customers;
import com.example.Museum.entities.Exhibits;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExhibitRepository extends CrudRepository<Exhibits, Long> {
    List<Exhibits> findByName(String name);
}
