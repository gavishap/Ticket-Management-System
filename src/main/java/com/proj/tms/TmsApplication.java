package com.proj.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.proj.Controllers", "com.proj.Services", "com.proj.sql", "com.proj.tms", "com.proj.Utils",
        "com.proj.DAO", "com.proj.Models" })
public class TmsApplication {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(TmsApplication.class, args);
    }
}
