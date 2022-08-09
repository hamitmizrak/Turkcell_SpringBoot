package com.hamitmizrak.ui.mvc;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.hamitmizrak.business.dto.ProductDto;

@Controller
public class ProductController {
	
	// NOT
	// ResponseEntity ==> kullanmıyorsak
	// ProductDto dto=restTemplate.getForObject(URL,ProductDto.class);
	
	// ResponseEntity ==> kullanıyorsak
	// ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(URL,
	// HttpMethod.GET, HttpEntity.EMPTY,ProductDto.class);
	
	// http://localhost:8080/client/controller/string
	// CLIENT
	// ResponseEntity gelmiyor ==> getForObject(URL,data.class)
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
	// CLIENT
	// ResponseEntity gelmiyor ==> getForObject(URL,data.class)
	@GetMapping("client/controller/productdto")
	@ResponseBody
	public ProductDto getClientObjectData() {
		final String URL = "http://localhost:8080/server/v1/object";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		ProductDto productDto = restTemplate.getForObject(URL, ProductDto.class);
		return productDto;
	}
	
	//// ResponseEntity ++++++++++////////////////////////////////////
	// http://localhost:8080/client/controller/path/productdto
	// http://localhost:8080/client/controller/path/productdto/0
	// http://localhost:8080/client/controller/path/productdto/44
	// CLIENT
	// getForObject
	// ResponseEntity gelmiyor ==> getForObject(URL,data.class)
	@GetMapping("client/controller/path/productdto/{id}")
	@ResponseBody
	public ProductDto getClientPathObjectData(@PathVariable(name = "id") Long id) {
		
		final String URL = "http://localhost:8080/server/v1/object/path/" + id;
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY,
				ProductDto.class);
		ProductDto productDto = responseEntity.getBody();
		return productDto;
	}
	
	// http://localhost:8080/client/controller/request/productdto
	// CLIENT
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
	
	// http://localhost:8080/client/controller/request/list/productdto
	// CLIENT
	// exchange ==>LIST
	@GetMapping("client/controller/request/list/productdto")
	@ResponseBody
	public List<ProductDto> getClientRequestListObjectData() {
		final String URL = "http://localhost:8080/server/v1/object/response/list";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ProductDto>> responseEntityList = restTemplate.exchange(URL, HttpMethod.GET,
				HttpEntity.EMPTY, new ParameterizedTypeReference<List<ProductDto>>() {
				});
		List<ProductDto> listProductDto = responseEntityList.getBody();
		return listProductDto;
	}
	
	// http://localhost:8080/client/controller/request/list/thymeleaf/productdto
	// CLIENT
	// exchange ==>LIST
	@GetMapping("client/controller/request/list/thymeleaf/productdto")
	public String getClientRequestListObjectDataThymeleaf(Model model) {
		final String URL = "http://localhost:8080/server/v1/object/response/list";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<ProductDto>> responseEntityList = restTemplate.exchange(URL, HttpMethod.GET,
				HttpEntity.EMPTY, new ParameterizedTypeReference<List<ProductDto>>() {
				});
		List<ProductDto> listProductDto = responseEntityList.getBody();
		model.addAttribute("rest_mvc_key", listProductDto);
		return "product_dto_rest_mvc";
	}
	
}
