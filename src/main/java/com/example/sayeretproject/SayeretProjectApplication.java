/*
 * Copyright (c) 25/6/2021
 * All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */
package com.example.sayeretproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableScheduling
public class SayeretProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SayeretProjectApplication.class, args);
        System.out.println("IoC was loaded!");

    }

}
