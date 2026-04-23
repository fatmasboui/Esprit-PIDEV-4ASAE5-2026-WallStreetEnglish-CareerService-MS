package com.example.career.service;

import com.example.career.entity.JobOffer;
import com.example.career.repository.JobOfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobOfferServiceTest {

    @Mock
    private JobOfferRepository repository;

    @InjectMocks
    private JobOfferService jobOfferService;

    private JobOffer offer;

    @BeforeEach
    void setUp() {
        offer = new JobOffer();
        offer.setId(1L);
        offer.setTitle("Software Engineer");
        offer.setDescription("Job description");
    }

    @Test
    void testGetAllOffers() {
        when(repository.findAll()).thenReturn(Arrays.asList(offer));
        List<JobOffer> results = jobOfferService.getAllOffers();
        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    void testGetOfferById() {
        when(repository.findById(1L)).thenReturn(Optional.of(offer));
        JobOffer result = jobOfferService.getOfferById(1L);
        assertNotNull(result);
        assertEquals("Software Engineer", result.getTitle());
    }

    @Test
    void testSaveOffer() {
        when(repository.save(any(JobOffer.class))).thenReturn(offer);
        JobOffer result = jobOfferService.saveOffer(new JobOffer());
        assertNotNull(result);
        assertEquals("Software Engineer", result.getTitle());
    }

    @Test
    void testDeleteOffer() {
        doNothing().when(repository).deleteById(1L);
        jobOfferService.deleteOffer(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
