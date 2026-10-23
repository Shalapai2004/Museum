package com.example.Museum.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Exhibits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition="TEXT", length = 1000)
    private String description;

    @Column(name = "state")
    private Boolean state = false;

    @Column(name = "quantity")
    private Integer quantity;

    public Exhibits() {

    }

    public Exhibits(String name, String description, Integer quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        if (quantity <= 0) {
            this.state = true;
        }
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="requests_exhibits",
            joinColumns = { @JoinColumn(name="exhibit_id", referencedColumnName="id") },
            inverseJoinColumns={ @JoinColumn(name="request_id", referencedColumnName="id") })
    private Set<Requests> requests = new HashSet<Requests>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="requests_exhibits",
            joinColumns = @JoinColumn(name="request_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="exhibit_id", referencedColumnName="id") )
    private Set<Exhibits> exhibits = new HashSet<Exhibits>(); 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
