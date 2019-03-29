package com.grupa8.projektsysbiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjektsysbizApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektsysbizApplication.class, args);
    }

}
