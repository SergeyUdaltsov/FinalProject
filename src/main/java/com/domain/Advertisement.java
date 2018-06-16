package com.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.serializer.AdvertisementDeserializator;
import com.serializer.AdvertisementSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@SequenceGenerator(name = "advert_id_gen", sequenceName = "advertisement_id", initialValue = 1, allocationSize = 1)
@JsonSerialize(using = AdvertisementSerializer.class)
@JsonDeserialize(using = AdvertisementDeserializator.class)
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advert_id_gen")
    private int id;

    private String title;

    private String text;

    private LocalDate date;

    private double price;

    private boolean isClosed;


    @ManyToOne
    @JoinColumn(name = "author_adv_fk")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "rubric_fk_id")
    private Rubric rubric;


    public Advertisement() {
    }


    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        this.isClosed = closed;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
