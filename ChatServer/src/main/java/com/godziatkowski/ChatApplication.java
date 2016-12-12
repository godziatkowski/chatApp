package com.godziatkowski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@SpringBootApplication
@EnableScheduling
public class ChatApplication {
    

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
            
    
}
