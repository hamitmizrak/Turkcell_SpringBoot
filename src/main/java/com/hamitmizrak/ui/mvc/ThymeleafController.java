package com.hamitmizrak.ui.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hamitmizrak.dto.ProductDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
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
	
	// ModelMapper => String
	// http://localhost:8080/controller/4
	@GetMapping("/controller/4")
	public String getThymeleaf4(Model model) {
		model.addAttribute("controller_key", "Ben Javadan Geldim");
		return "thymeleaf4";
	}
	
	// ModelMapper => Object
	// http://localhost:8080/controller/5
	@GetMapping("/controller/5")
	public String getThymeleaf5(Model model) {
		ProductDto productDto = ProductDto.builder().productId(0L).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf5";
	}
	
	// ModelMapper => List<Object>
	// http://localhost:8080/controller/6
	@GetMapping("/controller/6")
	public String getThymeleaf6(Model model) {
		List<ProductDto> listem = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			UUID uuid = UUID.randomUUID();
			listem.add(ProductDto.builder().productId(Long.valueOf(i)).productName("ürün adı: " + i)
					.productCode(uuid.toString()).build());
		}
		;
		model.addAttribute("controller_key", listem);
		log.info(listem);
		return "thymeleaf6";
	}
	
	// ModelMapper => List<Object> if,else
	// http://localhost:8080/controller/7
	@GetMapping("/controller/7")
	public String getThymeleaf7(Model model) {
		List<ProductDto> listem = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			UUID uuid = UUID.randomUUID();
			listem.add(ProductDto.builder().productId(Long.valueOf(i)).productName("ürün adı: " + i)
					.productCode(uuid.toString()).build());
		}
		;
		model.addAttribute("controller_key", listem);
		log.info(listem);
		return "thymeleaf7";
	}
	
	// ModelMapper => List<Object> if,else
	// http://localhost:8080/controller/8
	@GetMapping("/controller/8")
	public String getThymeleaf8(Model model) {
		List<ProductDto> listem = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			UUID uuid = UUID.randomUUID();
			listem.add(ProductDto.builder().productId(Long.valueOf(i)).productName("ürün adı: " + i)
					.productCode(uuid.toString()).build());
		}
		;
		model.addAttribute("controller_key", listem);
		log.info(listem);
		return "thymeleaf8";
	}
	
}
