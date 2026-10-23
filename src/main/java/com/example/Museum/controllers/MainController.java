package com.example.Museum.controllers;

import com.example.Museum.entities.Customers;
import com.example.Museum.entities.Exhibits;
import com.example.Museum.repositories.CustomerRepository;
import com.example.Museum.repositories.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/")
    public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String,Object> model) {
        model.put( "name", name);
        return "home";
    }





}