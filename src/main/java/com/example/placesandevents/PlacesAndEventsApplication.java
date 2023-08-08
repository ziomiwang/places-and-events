package com.example.placesandevents;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PlacesAndEventsApplication {

    @PostConstruct
    public void test(){
        System.out.println("qweqwewq");
    }

    public static void main(String[] args) {
        SpringApplication.run(PlacesAndEventsApplication.class, args);
    }

}
