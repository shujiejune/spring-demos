package org.example.bakery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryApplication.class, args);
    }

    @Bean
    public CommandLineRunner testCrud(PastryRepository repository) {
        return args -> {
            System.out.println("--- TESTING CRUD OPERATIONS ---");

            // 1. CREATE
            repository.create("Blueberry Muffin", 3.50f);
            System.out.println("1. Created: Blueberry Muffin");

            // 2. READ ALL
            List<Pastry> pastries = repository.findAll();
            System.out.println("2. Total Pastries in DB: " + pastries.size());

            // 3. READ BY ID
            // Find ID 1 ('pepperoni pizza')
            Pastry p = repository.findById(1);
            System.out.println("3. Found Pastry with ID 1: " + p.getName());

            // 4. UPDATE
            repository.update(1, "Updated Pepperoni", 44.99f);
            System.out.println("4. Updated ID 1");

            // 5. DELETE
            // Delete ID 2 ('veggie pizza')
            repository.delete(2);
            System.out.println("5. Deleted ID 2");

            System.out.println("--- TESTING COMPLETE ---");
        };
    }
}
