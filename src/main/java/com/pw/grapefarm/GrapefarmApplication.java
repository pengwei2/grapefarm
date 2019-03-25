package com.pw.grapefarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class GrapefarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrapefarmApplication.class, args);
    }

}
