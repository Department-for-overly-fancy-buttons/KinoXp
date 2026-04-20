package com.example.kinoxp.ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FeeType {
    @Id
    private String type;
    private double price;

    public FeeType(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public FeeType(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
