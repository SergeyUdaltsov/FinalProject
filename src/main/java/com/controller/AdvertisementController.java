package com.controller;

import com.domain.Advertisement;
import com.repository.AdvertisementRepository;
import com.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @PostMapping(value = "/save")
    public void save(@RequestBody Advertisement advertisement) {

        if (advertisement.getDate() == null) {
            advertisement.setDate(LocalDate.now());
        }
        advertisementService.save(advertisement);
    }


    @GetMapping(value = "/get/{id}")
    public Advertisement get(@PathVariable int id) {
        return advertisementService.get(id);
    }


    @GetMapping(value = "/get/all")
    public List<Advertisement> getAllAdv() {

        return advertisementRepository.findAll();
    }


    @GetMapping(value = "/get/by/author/{id}/{page}")
    public List<Advertisement> getAdvByAuthor(@PathVariable(name = "id") int id, @PathVariable(name = "page") int page) {

        return advertisementService.getAdvByAuthorId(id, page);
    }

    @GetMapping(value = "/get/page/{page}/{count}")
    public List<Advertisement> getPageAdvs(@PathVariable(name = "page") int page, @PathVariable(name = "count") int count) {

        return advertisementService.getAdvPage(page, count);
    }

    @GetMapping(value = "/count/author/{id}")
    public int getCountOfAdvByAuthor(@PathVariable(name = "id") int authorId) {
        return advertisementService.getCountAdvByAuthor(authorId);
    }

    @GetMapping(value = "/count")
    public int getCountOfAdv() {
        return advertisementRepository.countAdvertisement();
    }

    @GetMapping(value = "/filter/{rubricId}/{priceFrom}/{priceTo}")
    public List<Advertisement> getFilteredAdv(@PathVariable(name = "rubricId") int rubricId,
                                              @PathVariable(name = "priceFrom") double priceFrom,
                                              @PathVariable(name = "priceTo") double priceTo) {

        return advertisementService.showFilteredAdv(rubricId, priceFrom, priceTo);
    }


}



