package com.hamitmizrak.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/profile")
@Log4j2
// dış dünyaya açılan kapıdır
public class ProfileRestController {
	
	@Autowired
	IChooise chooise;
	
	// http://localhost:8080/profile/chooise1/java
	@GetMapping("chooise1/{data1}")
	public String getProfiles(@PathVariable(name = "data1") String data) {
		log.info(chooise.message(data));
		return chooise.message(data);
	}
	
	// http://localhost:8080/profile/chooise2/java
	@RequestMapping(path = "chooise2/{data2}", method = RequestMethod.GET)
	public String getProfiles2(@PathVariable(name = "data2") String data) {
		log.info(chooise.message(data));
		return chooise.message(data);
	}
	
}