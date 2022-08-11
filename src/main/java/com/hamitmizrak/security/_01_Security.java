package com.hamitmizrak.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
// Bu ise WebSecurity generated code çalışır ancak WebSecrity login'e redirect
// yapmaz
@EnableWebSecurity
public class _01_Security extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// httpBasic üzerinden sisteme giriş
		// http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		// formRegister
		http.authorizeRequests().anyRequest().authenticated().and().formLogin();
	}
	
}

// Yukarıdaki kod aslında @SpringBootApplication'da yazdığımız Exclude ile
// aynıdır'
// WebSecurity kapatmak
// @SpringBootApplication(exclude = {
// org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
// org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
// })