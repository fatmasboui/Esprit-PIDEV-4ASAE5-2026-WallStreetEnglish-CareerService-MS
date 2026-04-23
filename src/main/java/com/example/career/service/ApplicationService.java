package com.example.career.service;

import com.example.career.entity.Application;
import com.example.career.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository repository;

    public List<Application> getAllApplications() {
        return repository.findAll();
    }

    public Application getApplicationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Application saveApplication(Application application) {
        return repository.save(application);
    }

    public Application updateApplication(Long id, Application appDetails) {
        Application application = getApplicationById(id);
        if (application != null) {
            application.setStatus(appDetails.getStatus());
            application.setUserId(appDetails.getUserId());
            application.setJobOffer(appDetails.getJobOffer());
            return repository.save(application);
        }
        return null;
    }

    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }
}
