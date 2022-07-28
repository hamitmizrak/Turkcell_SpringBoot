package com.hamitmizrak.ui.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hamitmizrak.dto.ProductDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ThymeleafController {
	
	// http://localhost:8080/template/thymeleaf1
	@GetMapping("/template/thymeleaf1")
	@ResponseBody
	public String getThymeleaf1() {
		return "html sayfasız merhabalar";
	}
	
	// http://localhost:8080/template/thymeleaf2
	@GetMapping("/template/thymeleaf2")
	public String getThymeleaf2() {
		return "thymeleaf2";
	}
	
	// http://localhost:8080/template/thymeleaf3
	@GetMapping("/template/thymeleaf3")
	public String getThymeleaf3() {
		return "thymeleaf3";
	}
	
	// ModelMapper => String
	// http://localhost:8080/template/thymeleaf4
	@GetMapping("/template/thymeleaf4")
	public String getThymeleaf4(Model model) {
		model.addAttribute("controller_key", "Ben Javadan Geldim");
		return "thymeleaf4";
	}
	
	// ModelMapper => Object
	// http://localhost:8080/template/thymeleaf5
	@GetMapping("/template/thymeleaf5")
	public String getThymeleaf5(Model model) {
		ProductDto productDto = ProductDto.builder().productId(0L).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf5";
	}
	
	// ModelMapper => List<Object>
	// http://localhost:8080/template/thymeleaf6
	@GetMapping("/template/thymeleaf6")
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
	// http://localhost:8080/template/thymeleaf7
	@GetMapping("/template/thymeleaf7")
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
	// http://localhost:8080/template/thymeleaf8
	@GetMapping("/template/thymeleaf8")
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
	
	// ModelMapper => @PathVariable
	// http://localhost:8080/template/thymeleaf9/44
	@GetMapping("/template/thymeleaf9/{id}")
	public String getThymeleaf9(Model model, @PathVariable(name = "id") Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf9";
	}
	
}
