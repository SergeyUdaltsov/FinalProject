package com.repository;

import com.domain.Advertisement;
import com.domain.Author;
import com.domain.Rubric;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Transactional
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    @Query("SELECT a FROM Advertisement a ORDER BY a.date DESC ")
    List<Advertisement> getAdvertisementsOrderedByDate(Pageable pageable);

    @Query("SELECT a FROM Advertisement a WHERE a.author =:a_author ORDER BY a.date DESC ")
    List<Advertisement> getAdvertisementsOrderedByAuthorAndDate(@Param("a_author")Author author, Pageable pageable);

    @EntityGraph(attributePaths = {"author", "rubric"})
    Advertisement findById(int id);

    List<Advertisement> findAllByisClosedTrue();

    List<Advertisement> findAll();


    @Query("SELECT count (a) FROM Advertisement a")
    int countAdvertisement();

    int countAdvertisementByAuthorId(int id);

    @Query("SELECT a FROM Advertisement a WHERE a.rubric = :a_rubric AND a.price between :a_priceFrom AND :a_priceTo")
    List<Advertisement> findAllByRubricIdAndPrice(@Param("a_rubric")Rubric rubric,
                                                  @Param("a_priceFrom")double priceFrom,
                                                  @Param("a_priceTo") double priceTo);

}
