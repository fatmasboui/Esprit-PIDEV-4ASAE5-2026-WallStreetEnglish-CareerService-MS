package com.example.career.controller;

import com.example.career.entity.Application;
import com.example.career.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins ="http://localhost:4200")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @GetMapping
    public List<Application> getAll() {
        return service.getAllApplications();
    }

    @GetMapping("/{id}")
    public Application getById(@PathVariable Long id) {
        return service.getApplicationById(id);
    }

    @PostMapping
    public Application create(@RequestBody Application application) {
        return service.saveApplication(application);
    }

    @PutMapping("/{id}")
    public Application update(@PathVariable Long id, @RequestBody Application application) {
        return service.updateApplication(id, application);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteApplication(id);
    }
}
