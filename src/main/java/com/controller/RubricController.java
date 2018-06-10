package com.controller;

import com.domain.Rubric;
import com.service.RubricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "rubric")
public class RubricController {

    @Autowired
    private RubricService service;

    @PostMapping(value = "/save", consumes = "application/json")
    public void save(@RequestBody Rubric rubric) {
        service.save(rubric);
    }

    @GetMapping(value = "/get/{id}")
    public Rubric getRubricById(@PathVariable int id) {
        Rubric rubric = service.get(id);
        return rubric;
    }

    @GetMapping(value = "/get/all")
    public List<Rubric> getAllRubric() {
        return service.getAll();
    }

}
