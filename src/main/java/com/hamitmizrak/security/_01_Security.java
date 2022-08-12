package com.hamitmizrak.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.hamitmizrak.bean.PasswordEncoderMyBean;

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
		// http.authorizeRequests().anyRequest().authenticated().and().formLogin();
		
		// Kimlik Doğrulama: Authentication
		// antMatchers:Sadece bu sayfalar şifre girmeden sisteme girebilir.
		// http://localhost:8080/security/private
		// http://localhost:8080/security/public
		http.authorizeRequests().antMatchers("/", "/index", "login").permitAll().antMatchers("/security/public")
				.permitAll().antMatchers("/admin/logout").permitAll().anyRequest().authenticated().and().formLogin()
				.and().logout().logoutUrl("/admin/logout").invalidateHttpSession(true);
	}
	
	// http://localhost:8080/security/public
	// http://localhost:8080/security/private
	
	// Eğer Sürekli ben PasswordEncoder kullancaksam niye @Bean oluşturmuyorum ???
	@Autowired
	PasswordEncoderMyBean passwordEncoderMyBean;
	
	// Bu metotu ben yazdım ==> myUserPasswordRoles
	// bu metotudun görevi yeni kullanıcı eklemek ve o kullanıcıya rol vermek
	@Autowired
	public void myUserPasswordRoles(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		// inMemoryAuthentication = Database üzerinden değil Spring içinde bu veriler
		// NoEncrypted: yani Maskesiz saklanacak noop:Spring üzerinden maskelenmeden
		// sakalama yarar.
		// UNUTMA: ROLLER BÜYÜK HARFLE YAZILIR.
		// authenticationManagerBuilder.inMemoryAuthentication().withUser("hamitmizrak").password("{noop}root").roles("ADMIN");
		
		// Encrypted: Maskeli olması sha-1 sha-256
		// PasswordEncoder passwordEncoder =
		// PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		// Eğer Sürekli ben PasswordEncoder kullancaksam niye @Bean oluşturmuyorum ???
		authenticationManagerBuilder.inMemoryAuthentication().withUser("admin")
				.password(passwordEncoderMyBean.passwordEncoderMethod().encode("root")).roles("ADMIN").and()
				.withUser("writer").password(passwordEncoderMyBean.passwordEncoderMethod().encode("root"))
				.roles("WRITER");
	}
}
