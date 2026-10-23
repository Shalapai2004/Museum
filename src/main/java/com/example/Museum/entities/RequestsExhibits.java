package com.example.Museum.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "requests_exhibits")
public class RequestsExhibits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;





}
