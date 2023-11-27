package com.example.projectspring.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.projectspring")/* (scanBasePackages = "demo") */
@EnableJpaRepositories(basePackages = "com.example.projectspring") //(basePackages = "com.mainapp")
@EntityScan(basePackages = "com.example.projectspring.Entity")

public class ProjectSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectSpringApplication.class, args);
    }

}


/*

	@Bean

	CommandLineRunner initializeDatabase(CompteService cr) {
		return (args) -> {
			try {
			} catch(Exception e) {
				e.printStackTrace();
			}
		};
	}*/








