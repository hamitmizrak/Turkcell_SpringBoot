package com.hamitmizrak.myrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComputerBean {
	
	@Autowired
	IComputerRepository repository;
	
	@Bean
	public void computerAllData() {
		int counter = 0;
		for (int i = 1; i <= 20; i++) {
			ComputerEntity computerEntity = ComputerEntity.builder().computerName("Bilgisayar Adı")
					.computerPrice(i * 50).computerTrade("marka" + i).build();
			repository.save(computerEntity);
			counter++;
			
		}
		System.out.println(counter + " Tane" + ComputerEntity.class + " Eklendi");
	}
}
