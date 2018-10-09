package com.sdettest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name = "name")
    private String name;

    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Product(){

    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
