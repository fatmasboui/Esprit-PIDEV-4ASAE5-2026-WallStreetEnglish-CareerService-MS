import com.example.career.entity.Application;
import com.example.career.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository repository;

    public List<Application> getAllApplications() {
        log.info("Fetching all applications");
        return repository.findAll();
    }

    public Application getApplicationById(Long id) {
        log.info("Fetching application with id: {}", id);
        return repository.findById(id).orElse(null);
    }

    public Application saveApplication(Application application) {
        log.info("Saving new application");
        return repository.save(application);
    }

    public Application updateApplication(Long id, Application appDetails) {
        log.info("Updating application with id: {}", id);
        return repository.findById(id).map(application -> {
            application.setStatus(appDetails.getStatus());
            application.setUserId(appDetails.getUserId());
            application.setJobOffer(appDetails.getJobOffer());
            return repository.save(application);
        }).orElse(null);
    }

    public void deleteApplication(Long id) {
        log.info("Deleting application with id: {}", id);
        repository.deleteById(id);
    }
}

