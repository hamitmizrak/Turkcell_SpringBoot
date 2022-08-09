package com.hamitmizrak.ui.rest.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// http://localhost:8080/server/v1/object/path
	// http://localhost:8080/server/v1/object/path/44
	// SERVER
	@GetMapping({ "object/path", "object/path/{id}" })
	public ProductDto sendServerPathObjectData(@PathVariable(name = "id", required = false) Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı").productCode("ürün kodu")
				.productPrice(44).build();
		return productDto;
	}
	
	// http://localhost:8080/server/v1/object/response
	// http://localhost:8080/server/v1/object/response/44
	// SERVER
	@GetMapping({ "object/response", "object/response/{id}" })
	public ProductDto sendServerResponseObjectData(@PathVariable(name = "id", required = false) Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı").productCode("ürün kodu")
				.productPrice(23).build();
		return productDto;
	}
	
}
