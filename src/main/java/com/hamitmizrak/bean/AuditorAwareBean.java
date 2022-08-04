package com.hamitmizrak.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.hamitmizrak.audit.AuditorAwareImpl;

@Configuration
public class AuditorAwareBean {
	
	// org.springframework.data.domain.AuditorAware
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
	
}
