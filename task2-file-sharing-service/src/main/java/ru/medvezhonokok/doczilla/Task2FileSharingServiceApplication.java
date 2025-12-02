package ru.medvezhonokok.doczilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Task2FileSharingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Task2FileSharingServiceApplication.class, args);
    }

}
