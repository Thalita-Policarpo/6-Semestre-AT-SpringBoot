package br.com.edu.infnet.assessementspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.edu.infnet.assessementspringboot.repository")
@EnableMongoRepositories(basePackages = "br.com.edu.infnet.assessementspringboot.repository")
public class AssessementSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessementSpringBootApplication.class, args);
    }

}
