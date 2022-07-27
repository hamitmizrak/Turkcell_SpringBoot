package com.hamitmizrak.ui.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "thymeleaf2";
	}
	
	// http://localhost:8080/controller/3
	@GetMapping("/controller/3")
	public String getThymeleaf3() {
		return "thymeleaf3";
	}
	
	// http://localhost:8080/controller/4
	@GetMapping("/controller/4")
	public String getThymeleaf4(Model model) {
		model.addAttribute("controller_key", "Ben Javadan Geldim");
		return "thymeleaf4";
	}
	
}
