package com.maximedyma.tennis.service;

import com.maximedyma.tennis.ApplicationStatus;
import com.maximedyma.tennis.HealthCheck;
import com.maximedyma.tennis.data.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    @Autowired
    private HealthCheckRepository healthCheckRepository;

    public HealthCheck healthCheck() {
        Long applicationConnections = healthCheckRepository.countApplicationConnections();

        if (applicationConnections > 0) {
            return new HealthCheck(ApplicationStatus.OK, "Welcome to Dyma tennis !");
        } else {
            return new HealthCheck(ApplicationStatus.KO, "Dyma Tennis is not fully functional, please check your configuration");
        }
    }
}
