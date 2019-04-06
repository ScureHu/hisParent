package com.zcmu.nurse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import units.JwtUtil;

@SpringBootApplication
public class NurseApplication {
    public static void main(String[] args) {
        SpringApplication.run(NurseApplication.class);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
