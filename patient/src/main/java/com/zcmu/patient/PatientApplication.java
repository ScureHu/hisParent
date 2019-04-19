package com.zcmu.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import units.IdWorker;

@SpringBootApplication
@EnableEurekaClient
public class PatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
