package ca.levio.interviewrequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(
    scanBasePackages = {
		"ca.levio.interviewrequest",
		"ca.levio.interview"
    }
)
public class InterviewRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewRequestApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
