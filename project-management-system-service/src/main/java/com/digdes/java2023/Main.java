package com.digdes.java2023;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "com.digdes.java2023.model.employee")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(Main.class).headless(false).run(args);
    }
}
