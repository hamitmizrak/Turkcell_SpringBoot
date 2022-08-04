package com.hamitmizrak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// websecurity exclude
@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class })

// Auditing
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class TurkcellSpringBootApplication {
	
	// PSVM
	public static void main(String[] args) {
		SpringApplication.run(TurkcellSpringBootApplication.class, args);
		System.setProperty("spring.devtools.restart.enabled", "true");
		System.setProperty("java.awt.headless", "false"); // Disables headless
	} // psvm end
	
	// @Bean yazma yeri
	
}
