package com.zss.richtextformatdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RichTextFormatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RichTextFormatDemoApplication.class, args);
    }

}
