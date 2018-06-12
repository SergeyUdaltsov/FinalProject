package com.controller;

import com.domain.Rubric;
import com.service.RubricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.List;

@RestController
@RequestMapping(value = "rubric")
public class RubricController {

    @Autowired
    private RubricService service;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    @PostMapping(value = "/save")
    public ResponseEntity<Rubric> save(@RequestBody Rubric rubric) {

        int countOfMistakes = validator.validate(rubric).size();

        ResponseEntity<Rubric> response = new ResponseEntity<Rubric>(rubric, HttpStatus.NOT_FOUND);

        if (countOfMistakes == 0) {
            service.save(rubric);
            response = new ResponseEntity<Rubric>(rubric, HttpStatus.OK);
        }

        return response;
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
