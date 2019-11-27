package jp.co.example.ecommerce_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Ec201910bApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ec201910bApplication.class, args);
	}
	
	@Bean
	public RestTemplate setUpRestTemplate() {
		return new RestTemplate();
	}

}
