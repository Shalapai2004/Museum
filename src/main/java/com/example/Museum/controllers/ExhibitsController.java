package com.example.Museum.controllers;

import com.example.Museum.entities.Customers;
import com.example.Museum.entities.Exhibits;
import com.example.Museum.repositories.ExhibitRepository;
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
public class ExhibitsController {
    @Autowired
    private ExhibitRepository exhibitRepository;
    @GetMapping("/exhibits")
    public String exhibits(Map<String,Object> model) {
        Iterable<Exhibits> exhibits = exhibitRepository.findAll();
        model.put("exhibits",exhibits);
        return "exhibits";
    }
    @PostMapping("/exhibits/add")
    public String add(@RequestParam String name, @RequestParam String description, @RequestParam Integer quantity, Map<String, Object> model){
        Exhibits exhibit = new Exhibits(name,description,quantity);
        exhibitRepository.save(exhibit);

        Iterable<Exhibits> exhibits = exhibitRepository.findAll();
        model.put("exhibits",exhibits);
        return "redirect:/exhibits";
    }
    @PostMapping("/exhibits")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Exhibits> exhibits;
        if (filter != null && filter != "") {
            exhibits = exhibitRepository.findByName(filter);
        } else {
            exhibits = exhibitRepository.findAll();
        }
        model.put("exhibits",exhibits);
        return "exhibits";
    }
    @PostMapping("/exhibits/delete")
    public String delete(@RequestParam long delete, Map<String, Object> model) {
        if (!exhibitRepository.findById(delete).isEmpty() && exhibitRepository.findById(delete) != null ){
            Exhibits exhibitOptionalOnDelete = exhibitRepository.findById(delete).orElseThrow();
            Exhibits exhibitOnDelete = exhibitOptionalOnDelete ;
            exhibitRepository.delete(exhibitOnDelete);
            return "redirect:/exhibits";
        } else {
            return "errorchik";
        }
    }

    @GetMapping("/exhibits/{id}")
    public String exhibitsDetails(@PathVariable(value = "id") long id, Map<String,Object> model) {
        if (!exhibitRepository.existsById(id)) {
            return "redirect:/exhibits";
        }
        Optional<Exhibits> exhibits = exhibitRepository.findById(id);
        ArrayList<Exhibits> result = new ArrayList<>();
        exhibits.ifPresent(result::add);
        model.put("exhibits", result);
        return "exhibitDetails";
    }
    @GetMapping("/exhibits/{id}/edit")
    public String exhibitEdit(@PathVariable(value = "id") long id, Map<String,Object> model){
        if (!exhibitRepository.existsById(id)) {
            return "redirect:/customers";
        }
        Optional<Exhibits> exhibits = exhibitRepository.findById(id);
        ArrayList<Exhibits> result = new ArrayList<>();
        exhibits.ifPresent(result::add);
        model.put("exhibits", result);
        return "exhibitEdit";

    }

    @PostMapping("/exhibits/{id}/edit")
    public String exhibitsUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String description, @RequestParam Integer quantity, Map<String,Object> model){
        Exhibits exhibits = exhibitRepository.findById(id).orElseThrow();
        exhibits.setName(name);
        exhibits.setDescription(description);
        exhibits.setQuantity(quantity);
        exhibitRepository.save(exhibits);

        return "redirect:/exhibits";
    }

    @PostMapping("/exhibits/{id}/remove")
    public String deleteInDetails(@PathVariable(value = "id") long id, Map<String, Object> model) {
        if (!exhibitRepository.findById(id).isEmpty() && exhibitRepository.findById(id) != null ){
            Exhibits exhibitOptionalOnDelete = exhibitRepository.findById(id).orElseThrow();
            Exhibits exhibitOnDelete = exhibitOptionalOnDelete ;
            exhibitRepository.delete(exhibitOnDelete);
            return "redirect:/exhibits";
        } else {
            return "errorchik";
        }
    }

}
