package souza.oliveira.daniel.mscartoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MscartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscartoesApplication.class, args);
	}

}
