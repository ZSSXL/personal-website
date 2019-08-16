package com.zss.personalspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PersonalSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalSpaceApplication.class, args);
    }

}
