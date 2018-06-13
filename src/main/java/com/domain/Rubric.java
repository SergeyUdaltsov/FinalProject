package com.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "rubric_id_gen", sequenceName = "rubric_id", initialValue = 1, allocationSize = 1)
public class Rubric {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rubric_id_gen")
    private int id;


    @Column(unique = true)
    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "rubric")
    private Set<Advertisement> advertisements = new HashSet<>();

    public Rubric(String name) {
        this.name = name;
    }

    public Rubric() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }


}
