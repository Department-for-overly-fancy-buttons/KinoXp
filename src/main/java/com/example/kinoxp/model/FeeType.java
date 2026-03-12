package com.example.kinoxp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FeeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String type;
    private double price;


}
