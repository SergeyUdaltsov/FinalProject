package com.service;

import com.controller.Wrapper;
import com.domain.Advertisement;
import com.domain.Author;
import com.domain.Rubric;
import com.repository.AdvertisementRepository;
import com.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private RubricService rubricService;



    public void save(Advertisement advertisement) {
        advertisement.setDate(LocalDate.now());
        advertisementRepository.save(advertisement);
    }

    public Advertisement get(int id) {
        return advertisementRepository.findById(id);
    }

    public List<Advertisement> getAdvPage(int page, int count) {

        PageRequest pageRequest = PageRequest.of(page, count);

        return advertisementRepository.findAll(pageRequest).getContent();
    }

    public List<Advertisement> getAdvByAuthorId(int authorId, int page) {

        PageRequest pageRequest = PageRequest.of(page, 5);

        return advertisementRepository.findAllByAuthorId(authorId, pageRequest);
    }

    public int getCountAdvByAuthor(int authorId) {
        return advertisementRepository.countAdvertisementByAuthorId(authorId);
    }

    public List<Advertisement> showFilteredAdv(int rubricId, double priceFrom, double priceTo) {

        Rubric rubric = rubricService.get(rubricId);

        return advertisementRepository.findAllByRubricIdAndPrice(rubric, priceFrom, priceTo);
    }


    public void deleteById(int id) {
        advertisementRepository.deleteById(id);
    }
}
