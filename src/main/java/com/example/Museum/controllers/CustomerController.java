package com.example.Museum.controllers;

import com.example.Museum.entities.Customers;
import com.example.Museum.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public String customers(Map<String,Object> model) {
        Iterable<Customers> customers = customerRepository.findAll();
        model.put("customers",customers);
        
        return "customers";
        
    }

    @PostMapping("/customers/add")
    public String add(@RequestParam String name, Map<String, Object> model) {
        Customers customer = new Customers(name);
        customerRepository.save(customer);

        //Iterable<Customers> customers = customerRepository.findAll();
        //model.put("customers",customers);
        return "redirect:/customers";
    }
    @PostMapping("/customers")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Customers> customers;
        if (filter != null && filter != "") {
            customers = customerRepository.findByName(filter);
        } else {
            customers = customerRepository.findAll();
        }
        model.put("customers", customers);
        return "customers";
    }
    @PostMapping("/customers/delete")
    public String delete(@RequestParam long delete, Map<String, Object> model) {
        if (!customerRepository.findById(delete).isEmpty() && customerRepository.findById(delete) != null ){
            Customers customerOptionalOnDelete = customerRepository.findById(delete).orElseThrow();
            Customers customerOnDelete = customerOptionalOnDelete ;
            customerRepository.delete(customerOnDelete);
           // Iterable<Customers> customers = customerRepository.findAll();
            //model.put("customers",customers);
            return "redirect:/customers";
        } else {
            return "errorchik";
        }
    }
    @GetMapping("/customers/{id}")
    public String customerDetails(@PathVariable(value = "id") long id, Map<String,Object> model) {
        if (!customerRepository.existsById(id)) {
            return "redirect:/customers";
        }
        Optional<Customers> customers = customerRepository.findById(id);
        ArrayList<Customers> result = new ArrayList<>();
        customers.ifPresent(result::add);
        model.put("customers", result);
        return "customerDetails";
    }

    @GetMapping("/customers/{id}/edit")
    public String customerEdit(@PathVariable(value = "id") long id, Map<String,Object> model) {
        if (!customerRepository.existsById(id)) {
            return "redirect:/customers";
        }
        Optional<Customers> customers = customerRepository.findById(id);
        ArrayList<Customers> result = new ArrayList<>();
        customers.ifPresent(result::add);
        model.put("customers", result);
        return "customerEdit";
    }

    @PostMapping("/customers/{id}/edit")
    public String customersUpdate(@PathVariable(value = "id") long id,@RequestParam String name , Map<String,Object> model){
    Customers customers = customerRepository.findById(id).orElseThrow();
    customers.setName(name);
    customerRepository.save(customers);

    return "redirect:/customers";
    }

    @PostMapping("/customers/{id}/remove")
    public String deleteInDetails(@PathVariable(value = "id") long id, Map<String, Object> model) {
        if (!customerRepository.findById(id).isEmpty() && customerRepository.findById(id) != null ){
            Customers customerOptionalOnDelete = customerRepository.findById(id).orElseThrow();
            Customers customerOnDelete = customerOptionalOnDelete;
            customerRepository.delete(customerOnDelete);
            return "redirect:/customers";
        } else {
            return "errorchik";
        }
    }

}
