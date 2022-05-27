package com.src.kppproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KppProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KppProjectApplication.class, args);
    }

}
