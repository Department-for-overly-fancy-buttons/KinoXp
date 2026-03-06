package com.example.kinoxp.model;



public enum Role {
    COStUMER(0),
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
