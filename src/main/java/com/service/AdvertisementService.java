package com.service;

import com.domain.Advertisement;
import com.domain.Author;
import com.domain.Rubric;
import com.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@Service
@Transactional
public class AdvertisementService {

    public static final int SIZE = 5;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private RubricService rubricService;

    @Autowired
    private AuthorService authorService;


    public void deleteIfClosed() {
        List<Advertisement> advertisements = advertisementRepository.findAllByisClosedTrue();
        advertisements.stream().forEach(adv -> advertisementRepository.delete(adv));
    }

    public void save(Advertisement advertisement) {

        if (advertisement.getDate() == null) {
            advertisement.setDate(LocalDate.now());
        }
        advertisementRepository.save(advertisement);
    }

    public Advertisement get(int id) {
        return advertisementRepository.findById(id);
    }


    public List<Advertisement> getAdvPage(int page, int count) {

        PageRequest pageRequest = PageRequest.of(page, count);

        return advertisementRepository.getAdvertisementsOrderedByDate(pageRequest);
    }


    public List<Advertisement> getAdvByAuthorId(int authorId, int page) {

        Author author = authorService.getAuthorById(authorId);

        PageRequest pageRequest = PageRequest.of(page, SIZE);

        return advertisementRepository.getAdvertisementsOrderedByAuthorAndDate(author, pageRequest);
    }


    public int getCountAdvByAuthor(int authorId) {
        return advertisementRepository.countAdvertisementByAuthorId(authorId);
    }


    public List<Advertisement> showFilteredAdv(int rubricId, double priceFrom, double priceTo) {

        Rubric rubric = rubricService.get(rubricId);

        return advertisementRepository.findAllByRubricIdAndPrice(rubric, priceFrom, priceTo);
    }


}
