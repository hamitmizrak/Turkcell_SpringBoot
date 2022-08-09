package com.hamitmizrak.ui.mvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
		
		// @RestControllerdan veri almak istiyorsam
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
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		ProductDto productDto = restTemplate.getForObject(URL, ProductDto.class);
		return productDto;
	}
	
	// http://localhost:8080/client/controller/path/productdto
	// SERVER
	// getForObject
	@GetMapping("client/controller/path/productdto")
	@ResponseBody
	public ProductDto getClientPathObjectData() {
		final String URL = "http://localhost:8080/server/v1/object/path/44";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		ProductDto productDto = restTemplate.getForObject(URL, ProductDto.class);
		return productDto;
	}
	
	// http://localhost:8080/client/controller/request/productdto
	// SERVER
	// exchange
	@GetMapping("client/controller/request/productdto")
	@ResponseBody
	public ProductDto getClientRequestObjectData() {
		final String URL = "http://localhost:8080/server/v1/object/response/44";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY,
				ProductDto.class);
		ProductDto productDto = responseEntity.getBody();
		return productDto;
	}
	
}
