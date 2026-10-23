package com.example.Museum.controllers;

import com.example.Museum.entities.Customers;
import com.example.Museum.entities.Exhibits;
import com.example.Museum.entities.Requests;
import com.example.Museum.repositories.CustomerRepository;
import com.example.Museum.repositories.ExhibitRepository;
import com.example.Museum.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;
@Controller
public class RequestsController {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/requests")
    public String requests(Map<String,Object> model) {
        Iterable<Requests> requests = requestRepository.findAll();
        model.put("requests",requests);
        return "requests";
    }
    @PostMapping("/requests/add")
    public String add(@RequestParam Date exhibition_date_start, @RequestParam Date exhibition_date_end, @RequestParam Long IdCustomer, Map<String, Object> model){
        Requests request = new Requests(exhibition_date_start,exhibition_date_start, customerRepository.findById(IdCustomer).orElseThrow());
        requestRepository.save(request);
        return "redirect:/requests";
    }
}
