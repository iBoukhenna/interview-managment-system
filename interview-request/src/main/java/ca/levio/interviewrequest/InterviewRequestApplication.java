package ca.levio.interviewrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import ca.levio.interviewrequest.service.InterviewRequestProcessService;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class InterviewRequestApplication implements CommandLineRunner {

	@Autowired
	private InterviewRequestProcessService interviewRequestProcessService;

	public static void main(String[] args) {
		SpringApplication.run(InterviewRequestApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Override
    public void run(String... args) throws Exception {
		interviewRequestProcessService.createSchedulerTaskForAllTechnicalAdvisorSelectionProcess();
	}
}
