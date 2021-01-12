package ar.com.wolox.wchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WchallengeApplication {

	public static void main(String[] args) { SpringApplication.run(WchallengeApplication.class, args); }

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}