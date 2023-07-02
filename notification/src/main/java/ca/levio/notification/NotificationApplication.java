package ca.levio.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	scanBasePackages = {
		"ca.levio.notification",
		"ca.levio.mailmaker"
	}
)
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
