package com.example.career.service;

import com.example.career.entity.Application;
import com.example.career.repository.ApplicationRepository;
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
public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository repository;

    @InjectMocks
    private ApplicationService service;

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
        application.setId(1L);
        application.setStudentName("John Doe");
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(application));
        List<Application> results = service.getAll();
        assertEquals(1, results.size());
    }

    @Test
    void testGetById() {
        when(repository.findById(1L)).thenReturn(Optional.of(application));
        Application result = service.getById(1L);
        assertNotNull(result);
        assertEquals("John Doe", result.getStudentName());
    }

    @Test
    void testSave() {
        when(repository.save(any(Application.class))).thenReturn(application);
        Application result = service.save(new Application());
        assertNotNull(result);
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
