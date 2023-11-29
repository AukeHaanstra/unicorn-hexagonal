package nl.pancompany.unicorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class UnicornApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicornApplication.class, args);
	}

}
