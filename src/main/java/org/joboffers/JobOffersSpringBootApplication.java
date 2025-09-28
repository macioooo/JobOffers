package org.joboffers;

import org.joboffers.infrastructure.offers.http.OffersRestTemplateProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties(OffersRestTemplateProperties.class)
@EnableMongoRepositories
public class JobOffersSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobOffersSpringBootApplication.class, args);
    }

}