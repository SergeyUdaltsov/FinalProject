package com.repository;


import com.domain.Rubric;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RubricRepository extends JpaRepository<Rubric, Integer>{
    @Query("SELECT r FROM Rubric r LEFT JOIN FETCH r.advertisements WHERE r.id = :r_id")
    Rubric getRubricById(@Param(value = "r_id")int id);

    @EntityGraph(attributePaths = "advertisements")
    Rubric findById(int id);

    @EntityGraph(attributePaths = "advertisements")
    List<Rubric> findAll();



}
