package com.rendu.renduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RenduServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenduServiceApplication.class, args);
    }

}
