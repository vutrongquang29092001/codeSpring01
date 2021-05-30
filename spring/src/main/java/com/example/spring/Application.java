package com.example.spring;

import com.example.spring.controller.ProductController;
import com.example.spring.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling

public class Application {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(Application.class, args);
       ProductController productController = new ProductController();
        try {
            productController.scheduleFixedRateTaskAsync();
        } catch (ApiException e) {
            e.printStackTrace();
        }


    }





}
