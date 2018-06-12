package com.controller;

import com.domain.Author;
import com.repository.AuthorRepository;
import com.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping("/save")
    public Author saveAuthor(@RequestBody Author author) {

        Author savedAuthor = service.save(author);

        return savedAuthor;
    }

    @PostMapping(value = "/validate")
    public ResponseEntity<Author> validateAuthor(@RequestBody AuthorWrapper authorLoginWrapper) {

        Optional<Author> author = Optional.ofNullable(service.getByEmail(authorLoginWrapper.getEmail()));

        final boolean[] response = {false};

        Consumer<Author> validateAuthor = a ->{

            if (a.getPassword().equals(authorLoginWrapper.getPassword())) {
                response[0] = true;
            }
        };

        author.ifPresent(validateAuthor);

        return response[0] ? new ResponseEntity<Author>(author.get(), HttpStatus.OK) :
                new ResponseEntity<Author>((Author) null, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteAuthorById(@PathVariable int id) {
        service.deleteAuthorById(id);
    }

    @GetMapping(value = "/get/{id}")
    public Author get(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping(value = "/get/all")
    public List<Author> getAllAuthors() {
        return service.getAll();
    }

}
