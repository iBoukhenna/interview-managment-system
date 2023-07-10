package ca.levio.taskscheduler;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import ca.levio.taskscheduler.model.TechnicalAdvisorSelectionProcess;
import ca.levio.taskscheduler.service.TechnicalAdvisorSelectionProcessService;

@SpringBootApplication(
	scanBasePackages = {
		"ca.levio.taskscheduler",
		"ca.levio.messagequeue"
	}
)
@EnableScheduling
@EnableFeignClients(
	basePackages = "ca.levio.openfeignclients"
)
public class TaskSchedulerApplication implements CommandLineRunner {

	@Autowired
	private TechnicalAdvisorSelectionProcessService technicalAdvisorSelectionProcessService;

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Override
    public void run(String... args) throws Exception {
		Collection<TechnicalAdvisorSelectionProcess> technicalAdvisorSelectionProcessList = technicalAdvisorSelectionProcessService.selectAllTechnicalAdvisorSelectionProcess();
		technicalAdvisorSelectionProcessList.forEach(technicalAdvisorSelectionProcess -> technicalAdvisorSelectionProcessService.scheduleTechnicalAdvisorSelectionTask(technicalAdvisorSelectionProcess));
	}
}
