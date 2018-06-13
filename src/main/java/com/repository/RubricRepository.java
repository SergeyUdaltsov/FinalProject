package com.repository;


import com.domain.Rubric;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RubricRepository extends JpaRepository<Rubric, Integer> {


    @EntityGraph(attributePaths = "advertisements")
    Rubric findById(int id);

    @EntityGraph(attributePaths = "advertisements")
    List<Rubric> findAll();

}
