package com.hamitmizrak.ui.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThymeleafController {
	
	// http://localhost:8080/controller/1
	@GetMapping("/controller/1")
	@ResponseBody
	public String getThymeleaf1() {
		return "html sayfasız merhabalar";
	}
	
	// http://localhost:8080/controller/2
	@GetMapping("/controller/2")
	public String getThymeleaf2() {
		return "thymeleaf1";
	}
	
}
