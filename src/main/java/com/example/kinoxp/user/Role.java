package com.example.kinoxp.user;



public enum Role {
    CUSTOMER(0),
    EMPLOYEE(1),
    ADMIN(2);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
