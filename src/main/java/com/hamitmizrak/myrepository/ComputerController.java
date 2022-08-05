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
	
	@Autowired
	IMyRepositoryComputer iMyRepositoryComputer;
	
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
