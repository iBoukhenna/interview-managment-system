package ca.levio.interview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import ca.levio.interview.controller.InterviewController;

@SpringBootTest
class InterviewApplicationTests {

	@Autowired
	private InterviewController interviewController;

	@Test
	void contextLoads() {
		assertThat(interviewController).isNotNull();
	}
}
