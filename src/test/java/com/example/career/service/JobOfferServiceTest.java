package com.example.career.service;

import com.example.career.entity.JobOffer;
import com.example.career.repository.JobOfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobOfferServiceTest {

    @Mock
    private JobOfferRepository repository;

    @InjectMocks
    private JobOfferService jobOfferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOfferById() {
        JobOffer offer = new JobOffer();
        offer.setId(1L);
        offer.setTitle("Software Engineer");

        when(repository.findById(1L)).thenReturn(Optional.of(offer));

        JobOffer result = jobOfferService.getOfferById(1L);

        assertNotNull(result);
        assertEquals("Software Engineer", result.getTitle());
    }
}
