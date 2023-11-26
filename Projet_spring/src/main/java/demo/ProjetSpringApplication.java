package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@SuppressWarnings("deprecation")
@SpringBootApplication/* (scanBasePackages = "demo") */
@EnableJpaRepositories //(basePackages = "com.mainapp")
@EntityScan(basePackages = "Entity")

public class ProjetSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetSpringApplication.class, args);
	}

	@Bean

	CommandLineRunner initializeDatabase(/*UserService us, CustomerService cs, ModeratorService ms, ProductService ps, CreditCardService ccs, BasketService bs*/) {
		return (args) -> {
			try {
			} catch(Exception e) {
				e.printStackTrace();
			}
		};
	}

}





