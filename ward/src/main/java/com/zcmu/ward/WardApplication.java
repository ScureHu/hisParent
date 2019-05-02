package com.zcmu.ward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import units.IdWorker;
import units.JwtUtil;

@SpringBootApplication
@EnableEurekaClient
public class WardApplication {
    public static void main(String[] args) {
        SpringApplication.run(WardApplication.class);
    }

    @Bean
    public IdWorker idWorkerUtil(){
        return new IdWorker(1,0);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
