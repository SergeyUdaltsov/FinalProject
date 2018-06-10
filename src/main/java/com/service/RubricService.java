package com.service;

import com.domain.Rubric;
import com.repository.RubricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubricService {

    @Autowired
    private RubricRepository repository;

//
    public void save(Rubric rubric) {
        repository.save(rubric);
    }

    public Rubric get(int id) {
        return repository.findById(id);
    }

    public List<Rubric> getAll() {
        return repository.findAll();
    }

}
