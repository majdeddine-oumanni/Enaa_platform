package com.aprenant.aprenantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AprenantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AprenantServiceApplication.class, args);
    }

}
