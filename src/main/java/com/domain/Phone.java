package com.domain;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "phone_id_gen", sequenceName = "phone_id", initialValue = 1, allocationSize = 1)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_id_gen")
    private int id;

    private String number;

    public Phone(String number) {
        this.number = number;
    }

    public Phone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
