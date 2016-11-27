package com.godziatkowski.chatclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
public class ClientApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApp.class)
                .headless(false)
                .web(false)
                .run(args);
    }

}
