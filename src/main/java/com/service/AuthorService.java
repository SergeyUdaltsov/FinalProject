package com.service;

import com.domain.Author;
import com.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public Author save(Author author) {
        Author savedAuthor = repository.save(author);
        return savedAuthor;
    }

    public void deleteAuthorById(int id) {
        repository.deleteById(id);
    }

    public Author get(int id) {
        return repository.getAuthorById(id);
    }


}


