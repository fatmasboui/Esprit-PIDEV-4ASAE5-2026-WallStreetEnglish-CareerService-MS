package com.example.career.service;

import com.example.career.entity.JobOffer;
import com.example.career.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferService {

    @Autowired
    private JobOfferRepository repository;

    public List<JobOffer> getAllOffers() {
        return repository.findAll();
    }

    public JobOffer getOfferById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public JobOffer saveOffer(JobOffer offer) {
        return repository.save(offer);
    }

    public JobOffer updateOffer(Long id, JobOffer offerDetails) {
        JobOffer offer = getOfferById(id);
        if (offer != null) {
            offer.setTitle(offerDetails.getTitle());
            offer.setDescription(offerDetails.getDescription());
            offer.setRequiredLevel(offerDetails.getRequiredLevel());
            offer.setActive(offerDetails.isActive());
            return repository.save(offer);
        }
        return null;
    }

    public void deleteOffer(Long id) {
        repository.deleteById(id);
    }
}
