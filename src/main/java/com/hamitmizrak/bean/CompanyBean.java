package com.hamitmizrak.bean;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hamitmizrak.data.entity.CompanyEntity;
import com.hamitmizrak.data.repository.ICompanyRepository;

@Configuration
public class CompanyBean {
	
	// Inject
	@Autowired
	ICompanyRepository repository;
	
	// Random 10<=TAX<=1000
	private int randomNumber() {
		Random random = new Random();
		int tax = random.nextInt(90) + 10;
		System.out.println(tax);
		return tax;
	}
	
	@Bean
	public void companyAllData() {
		int counter = 0;
		for (int i = 1; i <= 5; i++) {
			CompanyEntity companyEntity = CompanyEntity.builder().companyEmailAddress("email adres" + i)
					.companyLogo("Logo" + i).companyMessage("Message" + i).companyName("company name" + i)
					.companyPassword("şifre" + i).companyTelephoneNumber("Telefon" + i).companyTaxNumber(randomNumber())
					.build();
			repository.save(companyEntity);
			counter++;
		}
	}
	
}
