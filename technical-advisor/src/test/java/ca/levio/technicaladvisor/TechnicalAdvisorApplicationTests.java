package ca.levio.technicaladvisor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import ca.levio.technicaladvisor.controller.TechnicalAdvisorController;

@SpringBootTest
class TechnicalAdvisorApplicationTests {

	@Autowired
	private TechnicalAdvisorController technicalAdvisorController;

	@Test
	void contextLoads() {
		assertThat(technicalAdvisorController).isNotNull();
	}
}
