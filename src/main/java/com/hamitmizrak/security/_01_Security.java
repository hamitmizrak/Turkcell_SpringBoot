package com.hamitmizrak.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Yukarıdaki kod aslında @SpringBootApplication'da yazdığımız Exclude ile
// aynıdır'
// WebSecurity kapatmak
// @SpringBootApplication(exclude = {
// org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
// org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
// })

// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
// Bu ise WebSecurity generated code çalışır ancak WebSecrity login'e redirect
// yapmaz
@EnableWebSecurity
public class _01_Security extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// httpBasic üzerinden sisteme giriş
		// http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		// formRegister üzerinden sisteme giriş
		http.authorizeRequests().anyRequest().authenticated().and().formLogin();
	}
	
	// Bu metotu ben yazdım ==> myUserPasswordRoles
	// bu metotudun görevi yeni kullanıcı eklemek ve o kullanıcıya rol vermek
	@Autowired
	public void myUserPasswordRoles(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		// inMemoryAuthentication=Database üzerinden değil Spring içinde bu veriler
		// //NoEncrpted: yani Maskesiz saklanacak noop:Spring üzerinden maskelenmeden
		// sakalama yarar.
		authenticationManagerBuilder.inMemoryAuthentication().withUser("hamitmizrak").password("{noop}root")
				.roles("ADMIN");
		
	}
	
}
