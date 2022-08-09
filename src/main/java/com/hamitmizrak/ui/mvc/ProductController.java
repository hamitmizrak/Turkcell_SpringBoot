package com.hamitmizrak.ui.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.hamitmizrak.business.dto.ProductDto;

@Controller
public class ProductController {
	
	// http://localhost:8080/client/controller/string
	// SERVER
	@GetMapping("client/controller/string")
	@ResponseBody
	public String getClientData() {
		final String URL = "http://localhost:8080/server/v1/manueljson";
		RestTemplate restTemplate = new RestTemplate();
		String jsonData = restTemplate.getForObject(URL, String.class);
		return jsonData;
	}
	
	// http://localhost:8080/client/controller/productdto
	// SERVER
	@GetMapping("client/controller/productdto")
	@ResponseBody
	public ProductDto getClientObjectData() {
		final String URL = "http://localhost:8080/server/v1/object";
		RestTemplate restTemplate = new RestTemplate();
		ProductDto productDto = restTemplate.getForObject(URL, ProductDto.class);
		return productDto;
	}
	
}
