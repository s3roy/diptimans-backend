package com.dashboard.visualgraphdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.dashboard")
@EnableJpaRepositories("com.dashboard.repository")
@EntityScan("com.dashboard.model")
public class VisualGraphDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualGraphDashboardApplication.class, args);
    }

}
