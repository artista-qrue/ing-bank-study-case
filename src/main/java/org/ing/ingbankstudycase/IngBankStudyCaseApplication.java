package org.ing.ingbankstudycase;

import org.ing.ingbankstudycase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IngBankStudyCaseApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(IngBankStudyCaseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(">>>> Users");
        userRepository.findAll().forEach(System.out::println);
    }
}
