package com.repository;

import com.domain.Author;
import com.domain.Rubric;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.phones LEFT JOIN FETCH " +
            "a.advertisements WHERE a.id = :a_id")
    Author getAuthorById(@Param(value = "a_id")int id);

    @EntityGraph(attributePaths = "advertisements")
    Author findByEmail(String email);

    @EntityGraph(attributePaths = "advertisements")
    List<Author> findAll();

    

}
