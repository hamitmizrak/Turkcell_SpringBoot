package com.hamitmizrak.ui.mvc;

import com.hamitmizrak.dto.ProductDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ThymeleafForm {
	
	// http://localhost:8080/product/form
	// default html sayfasınının ilk açıldığı zaman görünecek
	@GetMapping("/product/form")
	public String getForm(Model model) {
		model.addAttribute("key_form", new ProductDto());
		return "form";
	}
	
	// http://localhost:8080/product/form
	@PostMapping("/product/form")
	public String postForm(Model model, ProductDto productDto) {
		model.addAttribute("key_form", productDto);
		log.info(productDto);
		// save
		// file upload
		return "form";
	}
	
}
