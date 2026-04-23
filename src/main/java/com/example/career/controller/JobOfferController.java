package com.example.career.controller;

import com.example.career.entity.JobOffer;
import com.example.career.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-offers")
@CrossOrigin(origins ="http://localhost:4200")
public class JobOfferController {

    @Autowired
    private JobOfferService service;

    @GetMapping
    public List<JobOffer> getAll() {
        return service.getAllOffers();
    }

    @GetMapping("/{id}")
    public JobOffer getById(@PathVariable Long id) {
        return service.getOfferById(id);
    }

    @PostMapping
    public JobOffer create(@RequestBody JobOffer offer) {
        return service.saveOffer(offer);
    }

    @PutMapping("/{id}")
    public JobOffer update(@PathVariable Long id, @RequestBody JobOffer offer) {
        return service.updateOffer(id, offer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteOffer(id);
    }
}
