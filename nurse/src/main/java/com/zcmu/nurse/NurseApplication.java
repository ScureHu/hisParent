package com.zcmu.nurse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import units.JwtUtil;

@SpringBootApplication
@EnableEurekaClient
public class NurseApplication {
    public static void main(String[] args) {
        SpringApplication.run(NurseApplication.class);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
