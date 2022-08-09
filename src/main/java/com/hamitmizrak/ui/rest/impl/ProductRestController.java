package com.hamitmizrak.ui.rest.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamitmizrak.business.dto.ProductDto;

@RestController
@RequestMapping("/server/v1")
public class ProductRestController {
	
	// http://localhost:8080/server/v1/manueljson
	// SERVER
	@GetMapping("manueljson")
	public String sendServerData() {
		return "{\r\n" + "  \"adi\":\"Hamit\",\r\n" + "  \"soyadi\":\"Mızrak\"\r\n" + "}";
	}
	
	// http://localhost:8080/server/v1/object
	// SERVER
	@GetMapping("object")
	public ProductDto sendServerObjectData() {
		ProductDto productDto = ProductDto.builder().productId(1L).productName("ürün adı").productCode("ürün kodu")
				.productPrice(44).build();
		return productDto;
	}
	
}
