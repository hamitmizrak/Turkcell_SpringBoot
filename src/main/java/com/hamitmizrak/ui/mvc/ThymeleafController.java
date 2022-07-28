package com.hamitmizrak.ui.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hamitmizrak.dto.ProductDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/////////// @PathVariable///////////////////////////////////////////////////////////////
	// ModelMapper => @PathVariable
	// http://localhost:8080/template/thymeleaf9/44
	@GetMapping("/template/thymeleaf9/{id}")
	public String getThymeleaf9(Model model, @PathVariable(name = "id") Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf9";
	}
	
	// ModelMapper => @PathVariable
	// http://localhost:8080/template/thymeleaf10
	// http://localhost:8080/template/thymeleaf10/44
	@GetMapping({ "/template/thymeleaf10", "/template/thymeleaf10/{id}" })
	public String getThymeleaf10(Model model, @PathVariable(name = "id", required = false) Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf10";
	}
	// 1:information
	// 2:succes
	// 3:redirect
	// 4:client
	// 5:server
	
	// ModelMapper => @PathVariable
	// http://localhost:8080/template/thymeleaf11
	// http://localhost:8080/template/thymeleaf11/44
	// http://localhost:8080/template/thymeleaf11/44/
	// http://localhost:8080/template/thymeleaf11/44/Bilgisayarurunu
	@GetMapping({ "/template/thymeleaf11/", "/template/thymeleaf11/{id}", "/template/thymeleaf11/{urun}",
			"/template/thymeleaf11/{id}/{urun}" })
	public String getThymeleaf11(Model model, @PathVariable(name = "id", required = false) Long id,
			@PathVariable(name = "urun", required = false) String urun) {
		ProductDto productDto = ProductDto.builder().productId(id).productName(urun).productCode("ürün kodu 1254X")
				.build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf11";
	}
	
	/////////// @RequestParam///////////////////////////////////////////////////////////////
	// ModelMapper => @RequestParam
	// http://localhost:8080/template/thymeleaf12?id=44
	// @GetMApping sonuna bir işlem yapmayan slash koymalayalım yoksa yeni
	// attirbuters ister
	@GetMapping("/template/thymeleaf12")
	public String getThymeleaf12(Model model, @RequestParam(name = "id") Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf12";
	}
	
	// ModelMapper => @RequestParam
	// http://localhost:8080/template/thymeleaf12_1
	// http://localhost:8080/template/thymeleaf12_1?id=44
	// @GetMApping sonuna bir işlem yapmayan slash koymalayalım yoksa yeni
	// attirbuters ister
	@GetMapping("/template/thymeleaf12_1")
	public String getThymeleaf12_1(Model model,
			@RequestParam(name = "id", required = false, defaultValue = "1") Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf12_1";
	}
	
	// ModelMapper => @RequestParam
	// http://localhost:8080/template/thymeleaf13?id=44&product=Bilgisayar
	// @GetMapping sonuna bir işlem yapmayan slash koymalayalım yoksa yeni
	// attirbuters ister
	@GetMapping("/template/thymeleaf13")
	public String getThymeleaf12(Model model, @RequestParam(name = "id") Long id,
			@RequestParam(name = "product") String product
	
	) {
		ProductDto productDto = ProductDto.builder().productId(id).productName(product).productCode("ürün kodu 1254X")
				.build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf13";
	}
	
	/////////// @PathVariable @RequestParam/////////// /////////// ///////////
	/////////// ///////////
	
	// http://localhost:8080/template/thymeleaf14/44?product=Bilgisayar&product_code=code444
	@GetMapping("/template/thymeleaf14/{id}")
	public String getThymeleaf14(Model model, @PathVariable(name = "id") Long id,
			@RequestParam(name = "product") String product, @RequestParam(name = "product_code") String productCode
	
	) {
		ProductDto productDto = ProductDto.builder().productId(id).productName(product).productCode(productCode)
				.build();
		model.addAttribute("controller_key", productDto);
		return "thymeleaf14";
	}
	
	/////////// validation///////////////////////////////////////////////////////////////////
	
	// http://localhost:8080/template/thymeleaf15
	// http://localhost:8080/template/thymeleaf15/0
	// http://localhost:8080/template/thymeleaf15/44
	@GetMapping({ "/template/thymeleaf15", "/template/thymeleaf15/{id}" })
	public String getThymeleaf15(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id == null) {
			model.addAttribute("validation_key", "not found");
		} else if (id == 0) {
			model.addAttribute("validation_key", "bad request");
		} else {
			ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı")
					.productCode("ürün kodu 1254X").build();
			model.addAttribute("controller_key", productDto);
		}
		return "thymeleaf15";
	}
	
	/////////// otherfile///////////////////////////////////////////////////////////////////
	// http://localhost:8080/template/thymeleaf16/44
	@GetMapping({ "/template/thymeleaf16", "/template/thymeleaf16/{id}" })
	public String getThymeleaf16(Model model, @PathVariable(name = "id", required = false) Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı")
				.productCode("ürün kodu 1254X").build();
		model.addAttribute("controller_key", productDto);
		return "admin/thymeleaf16";
	}
	
}
