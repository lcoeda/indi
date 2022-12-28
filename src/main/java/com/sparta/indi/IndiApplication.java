package com.sparta.indi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IndiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndiApplication.class, args);
    }

}
