package com.hamitmizrak.ui.rest.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hamitmizrak.business.dto.ProductDto;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/server/v1")
@Log4j2
@CrossOrigin
public class ProductRestController {
	
	// @RestController
	// @RequestMapping()
	// @RequestBody
	// @RequestHeader
	
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
	
	///////////////////// ResponseEntity////////////////////////////////////////////////////////////////////////////////////////
	// http://localhost:8080/server/v1/object/path
	// http://localhost:8080/server/v1/object/path/0
	// http://localhost:8080/server/v1/object/path/44
	// SERVER
	@GetMapping({ "object/path", "object/path/{id}" })
	@ResponseBody
	public ResponseEntity<?> sendServerPathObjectData(@PathVariable(name = "id", required = false) Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı").productCode("ürün kodu")
				.productPrice(44).build();
		if (id == null) {
			log.error("404: notfound");
			return ResponseEntity.notFound().build();
		} else if (id == 0) {
			log.error("400: bad request");
			return ResponseEntity.badRequest().build();
		}
		log.info(productDto);
		return ResponseEntity.ok(productDto);
	}
	
	// http://localhost:8080/server/v1/object/response
	// http://localhost:8080/server/v1/object/response/44
	// SERVER
	@GetMapping({ "object/response", "object/response/{id}" })
	public ResponseEntity<ProductDto> sendServerResponseObjectData(
			@PathVariable(name = "id", required = false) Long id) {
		ProductDto productDto = ProductDto.builder().productId(id).productName("ürün adı").productCode("ürün kodu")
				.productPrice(23).build();
		return ResponseEntity.ok(productDto);
	}
	
	// http://localhost:8080/server/v1/object/response/list
	// SERVER
	@GetMapping("object/response/list")
	public ResponseEntity<List<ProductDto>> sendServerResponseListObjectData() {
		// ArrayList
		List<ProductDto> productDtoList = new LinkedList<ProductDto>();
		
		// 5 tane ProductList'i oluşturdum
		for (int i = 1; i <= 5; i++) {
			ProductDto productDto = ProductDto.builder().productId(Long.valueOf(i)).productName("ürün adı: " + i)
					.productCode("ürün kodu: " + i).productPrice(i * 10).build();
			productDtoList.add(productDto);
		}
		return ResponseEntity.ok(productDtoList);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// POST ==> VOID ==> EKLE ==> @PostMapping
	// http://localhost:8080/server/v1/product/void/post
	// SERVER
	// Dikkat: postMapping mutlaka ==> @RequestBody yazmalısınız
	@PostMapping("product/void/post")
	public void productVoidPost(@RequestBody ProductDto productDto) {
		log.info(productDto);
		// database
		// file
		// socket
		// api
	}
	
	// POST ==> PRODUCT ==> EKLE ==> @PostMapping
	// http://localhost:8080/server/v1/product/object/post
	// SERVER
	// Dikkat: postMapping mutlaka ==> @RequestBody yazmalısınız
	@PostMapping("product/object/post")
	public ProductDto productProductPost(@RequestBody ProductDto productDto) {
		log.info(productDto);
		// database
		// file
		// socket
		// api
		return productDto;
	}
	
	// POST ==>RESPONSEENTITY==> PRODUCT ==> EKLE ==> @PostMapping
	// http://localhost:8080/server/v1/product/object/responseentity
	// SERVER
	// Dikkat: postMapping mutlaka ==> @RequestBody yazmalısınız
	@PostMapping("product/object/responseentity")
	public ResponseEntity<ProductDto> productProductPostResponseEntity(@RequestBody ProductDto productDto) {
		log.info(productDto);
		
		return ResponseEntity.ok(productDto);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// PUT ==> PRODUCT ==> GÜNCELLEME ==> @PutMapping
	// http://localhost:8080/server/v1/product/put/responseentity
	// SERVER
	// Dikkat: putMapping mutlaka ==> @RequestBody yazmalısınız
	@PutMapping("product/put/responseentity")
	public ResponseEntity<ProductDto> productProductPutResponseEntity(@RequestBody ProductDto productDto) {
		log.info(productDto);
		return ResponseEntity.ok(productDto);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// DELETE ==> Void ==> SİLMEK ==> @DeleteMapping
	// http://localhost:8080/server/v1/product/delete/1
	// SERVER
	// Dikkat: putMapping mutlaka ==> @RequestBody yazmalısınız
	@DeleteMapping("product/delete/{id}")
	public void productProductDeleteResponseEntity(@PathVariable(name = "id") Long id) {
		log.info(ProductDto.class + " " + id + " nolu id silindi");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// @RestController ==> jQuery'e veri göndermek ve Html5 üzerinde veri göstermek
	
	// http://localhost:8080/server/v1/restcontroller/jquery/cors
	@CrossOrigin
	@GetMapping("restcontroller/jquery/cors")
	public String getJquery() {
		return "Merhababalar ben Java RestControllardan geldim";
	}
	
	// Reflection +Annotaion
	// Java 8 Stream
	// Java 8 Optional
	// Thread + Semophore +synchronized
	//
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// HEADER
	// Request ==> @Controller
	// Response ==> @RestController
	// http://localhost:8080/server/v1/restcontroller/response/header
	@GetMapping("restcontroller/response/header")
	public ResponseEntity<?> getRestControllerResponseHeader() {
		return ResponseEntity.ok().header("key_restcontroller_header", "@RestController Verileri")
				.body("@RestController Body");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ROLLER DEĞİŞTİ
	// HEADER
	// Request ==> @RestController
	// Response ==> @Controller
	// http://localhost:8080/server/v1/restcontroller/request/header
	@GetMapping("restcontroller/request/header")
	public ResponseEntity<?> getRestControllerRequestHeader(
			@RequestHeader(value = "key_controller_header", defaultValue = "default data yazdım") String data) {
		// @Controllerdaki oluşturulmuş Headeri @RestController aldı
		String headerData = "@ResponseController: " + data;
		log.error("@RestController ==> " + headerData);
		return ResponseEntity.ok(headerData);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// COOKIE
	// Request ==> @Controller
	// Response ==> @RestController
	// import org.springframework.http.HttpHeaders;
	// http://localhost:8080/server/v1/restcontroller/response/cookie
	@GetMapping("restcontroller/response/cookie")
	public ResponseEntity<?> getRestControllerResponseCookie() {
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, "key_restcontroller_cookie")
				.body("@RestController Coookie Body");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ROLLER DEĞİŞTİ
	// COOKIE
	// Request ==> @RestController
	// Response ==> @Controller
	// http://localhost:8080/server/v1/restcontroller/request/cookie
	@GetMapping("restcontroller/request/cookie")
	public ResponseEntity<?> getRestControllerRequestCookie(
			@CookieValue(value = "key_controller_response_cookie", defaultValue = "default cookie değeri") String data) {
		// @Controllerdaki oluşturulmuş Cookie @RestController aldı
		String headerData = "@ResponseController: " + data;
		log.error("@RestController ==> " + headerData);
		return ResponseEntity.ok(headerData);
	}
	
}
