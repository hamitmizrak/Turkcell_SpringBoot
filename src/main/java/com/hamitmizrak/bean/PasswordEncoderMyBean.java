package com.hamitmizrak.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderMyBean {
	
	// Dikkatttt: PasswordEncoder yaz kendi Classını yazmak
	@Bean
	public PasswordEncoder passwordEncoderMethod() {
		return new BCryptPasswordEncoder();
	}
	
}
