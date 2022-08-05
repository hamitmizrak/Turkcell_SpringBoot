package com.hamitmizrak.myrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ComputerController {
	
	// CRUD Repository
	@Autowired
	IComputerRepository repository;
	
	// Benim yazdığım Repository
	@Autowired
	IMyRepositoryComputer iMyRepositoryComputer;
	
	// http://localhost:8080/computer/all/save
	@GetMapping("computer/all/save")
	@ResponseBody
	public String getMyComputerSave() {
		int counter = 0;
		for (int i = 1; i <= 20; i++) {
			ComputerEntity computerEntity = ComputerEntity.builder().computerName("Bilgisayar Adı")
					.computerPrice(i * 50).computerTrade("marka" + i).build();
			repository.save(computerEntity);
			counter++;
		}
		System.out.println(counter + " Tane" + ComputerEntity.class + " Eklendi");
		return counter + " Tane" + ComputerEntity.class + " Eklendi";
	}
	
	// http:localhost:8080/computer/minprice/200
	@GetMapping("computer/minprice/{price}")
	@ResponseBody
	public String getComputerMyRepositoryData(@PathVariable(name = "price") Double price) {
		Iterable<ComputerEntity> computerList = iMyRepositoryComputer.findComputerPriceMin(price);
		for (ComputerEntity temp : computerList) {
			log.info(temp);
		}
		// return computerList.toString();
		return computerList + " ";
	}
}
