package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GlofoxApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        System.out.println("WELCOME GLOFOX IT TEAM");
        SpringApplication.run(GlofoxApplication.class, args);
    }
}
