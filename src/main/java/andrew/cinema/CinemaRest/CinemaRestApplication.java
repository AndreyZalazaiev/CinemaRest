package andrew.cinema.CinemaRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CinemaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaRestApplication.class, args);
	}

}
