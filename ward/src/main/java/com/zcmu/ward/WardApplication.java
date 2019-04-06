package com.zcmu.ward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WardApplication {
    public static void main(String[] args) {
        SpringApplication.run(WardApplication.class);
    }
}
