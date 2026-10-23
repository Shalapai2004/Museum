package com.example.Museum.repositories;

import com.example.Museum.entities.Exhibits;
import com.example.Museum.entities.Requests;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Requests, Long> {
    List<Requests> findByStatus(Requests.enumStatus enumStatus);}
