package com.Fastech.RegistrationApp;

import com.Fastech.RegistrationApp.entities.AppRole;
import com.Fastech.RegistrationApp.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class RegistrationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationAppApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            accountService.save(new AppRole(null, "USER"));
            accountService.save(new AppRole(null, "ADMIN"));
            Stream.of("user1", "user2", "user3", "admin").forEach(un -> {
                accountService.saveUser(un, "1234", "1234");
            });
            accountService.addRoleToUser("admin", "ADMIN");
        };
    }

    @Bean
    BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }

}
