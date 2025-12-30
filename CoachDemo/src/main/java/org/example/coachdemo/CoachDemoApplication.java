package org.example.coachdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoachDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(Player player) {
        return args -> {
            System.out.println("--- Workout Schedules ---");
            player.showWorkOut();
        };
    }
}
