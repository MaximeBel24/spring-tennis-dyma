package com.maximedyma.tennis.data;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HealthCheckRepository {

    @Autowired
    private EntityManager entityManager;

    public Long countApplicationConnections() {
        String applicationConnectionsQuery = "select count(*) form pg_stat_activity where application_name = 'PostgreSQL JDBC Driver'";
        return (Long) entityManager.createNativeQuery(applicationConnectionsQuery).getSingleResult();
    }
}
