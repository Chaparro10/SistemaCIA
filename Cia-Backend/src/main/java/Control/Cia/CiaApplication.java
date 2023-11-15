package Control.Cia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "Control.Cia.models")
public class CiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiaApplication.class, args);
	}

}
