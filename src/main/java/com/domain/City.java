package com.domain;

import javax.persistence.Entity;

public enum City {


    DNEPR("Dnepr"),
    KHARKIV("Kharkiv"),
    KYIV("Kyiv"),
    ODESSA("Odessa"),
    LVIV("Lviv"),
    DONETSK("Donetsk");

    private String city;

    City(String city) {
        this.city = city;
    }

    City() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "City.txt{" +
                "citiesList='" + city + '\'' +
                '}';
    }
}
