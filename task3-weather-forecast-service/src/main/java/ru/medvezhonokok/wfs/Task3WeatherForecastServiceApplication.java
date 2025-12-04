package ru.medvezhonokok.wfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class Task3WeatherForecastServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Task3WeatherForecastServiceApplication.class, args);
    }

}
