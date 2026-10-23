package com.example.Museum.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private enumStatus status = enumStatus.processing;
   // @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "exhibition_date_start")
    private Date exhibition_date_start;
   // @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "exhibition_date_end")
    private Date exhibition_date_end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customers customer;

    public Requests() {

    }
    public Requests(Date exhibition_date_start, Date exhibition_date_end, Customers customer) {
        this.exhibition_date_start = exhibition_date_start;
        this.exhibition_date_end = exhibition_date_end;
        this.customer = customer;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public enumStatus getStatus() {
        return status;
    }

    public void setStatus(enumStatus status) {
        this.status = status;
    }

    public Date getExhibition_date_start() {
        return exhibition_date_start;
    }

    public void setExhibition_date_start(Date exhibition_date_start) {
        this.exhibition_date_start = exhibition_date_start;
    }

    public Date getExhibition_date_end() {
        return exhibition_date_end;
    }

    public void setExhibition_date_end(Date exhibition_date_end) {
        this.exhibition_date_end = exhibition_date_end;
    }

    public Customers getCustomers() {
        return customer;
    }

    public void setCustomers(Customers customers) {
        this.customer = customers;
    }
    public enum enumStatus {

        approved,
        declined,
        processing
    }

}
