package com.hamitmizrak.ui.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.hamitmizrak.business.dto.ProductDto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// EKLE ==>Void ==> @PostMapping
	// http://localhost:8080/client/product/void/post
	// CLIENT
	@GetMapping("client/product/void/post")
	@ResponseBody
	public String productVoidPost() {
		
		// ProductInstance
		ProductDto productDto = ProductDto.builder().productId(1L).productName("ürün adı").productCode("ürün kodu")
				.productPrice(44).build();
		// PATH
		final String URL = "http://localhost:8080/server/v1/product/void/post";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(URL, productDto, Void.class);
		
		return ProductDto.class + " Ekleme Tamamdır";
		
	}
	
	// EKLE ==> Object ==> @PostMapping
	// http://localhost:8080/client/product/post?product_name=ürün44&product_code=44ABCDCodes
	// CLIENT
	@GetMapping("client/product/post")
	@ResponseBody
	public String productObjectPost(@RequestParam(name = "product_name") String productName,
			@RequestParam(name = "product_code") String productCode) {
		
		// ProductInstance
		ProductDto productDto = ProductDto.builder().productId(1L).productName(productName).productCode(productCode)
				.productPrice(44).build();
		// PATH
		final String URL = "http://localhost:8080/server/v1/product/object/post";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		
		ProductDto productDto2 = restTemplate.postForObject(URL, productDto, ProductDto.class);
		// database api fileIO eklenebilinir.
		
		return productDto2 + " Ekleme Tamamdır";
		
	}
	
	// EKLE ==>ResponseEntity==> Object ==> @GetMapping
	// http://localhost:8080/client/product/post/responseentity?product_name=ürün44&product_code=44ABCDCodes
	// CLIENT
	@GetMapping("client/product/post/responseentity")
	@ResponseBody
	public String productObjectPostResponseEntity(@RequestParam(name = "product_name") String productName,
			@RequestParam(name = "product_code") String productCode) {
		
		// ProductInstance
		ProductDto productDto44 = ProductDto.builder().productId(1L).productName(productName).productCode(productCode)
				.productPrice(44).build();
		// PATH
		final String URL = "http://localhost:8080/server/v1/product/object/responseentity";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		
		// Eğer restcontroller postmapping olarak ResponseEntity gönderiyorsa
		HttpEntity<ProductDto> httpEntity = new HttpEntity<ProductDto>(productDto44);
		ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity,
				ProductDto.class);
		
		ProductDto productDto = responseEntity.getBody();
		
		return productDto + " Ekleme Tamamdır";
		
	}
	
	//// +++++ PUT GÜNCELLEMEK //////
	// GÜNCELLE ==>ResponseEntity==> Object ==> @GetMapping
	// http://localhost:8080/client/product/put/responseentity?product_id=1&product_name=ürün44&product_code=44ABCDCodes
	// CLIENT
	@GetMapping("client/product/put/responseentity")
	@ResponseBody
	public String productObjectPutResponseEntity(@RequestParam(name = "product_id") Long productId,
			@RequestParam(name = "product_name") String productName,
			@RequestParam(name = "product_code") String productCode) {
		
		// ProductInstance
		ProductDto productDto44 = ProductDto.builder().productId(productId).productName(productName)
				.productCode(productCode).productPrice(44).build();
		
		// PATH
		final String URL = "http://localhost:8080/server/v1/product/put/responseentity";
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		
		// Eğer restcontroller postmapping olarak ResponseEntity gönderiyorsa
		// dilerseniz HttpEntity oluşturmayı ResponseEntity yapabiliriz.
		// HttpEntity<ProductDto> httpEntity = new HttpEntity<ProductDto>(productDto44);
		ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT,
				new HttpEntity<ProductDto>(productDto44), ProductDto.class);
		
		ProductDto productDto = responseEntity.getBody();
		return productDto + " Güncelleme Tamamdır";
	}
	
	//// +++++ DELETE SİLMEK //////
	// SİLMEK ==> Object ==> @GetMapping
	// http://localhost:8080/client/product/delete/4
	// CLIENT
	@GetMapping("client/product/delete/{product_id}")
	@ResponseBody
	public String productObjectDeleteResponseEntity(@PathVariable(name = "product_id") Long productId) {
		
		// PATH
		final String URL = "http://localhost:8080/server/v1/product/delete/" + productId;
		
		// @RestControllerdan veri almak istiyorsam
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(URL, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
		
		return productId + " nolu ID Silindi.";
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// HEADER
	// Request ==> @Controller
	// Response ==> @RestController
	// http://localhost:8080/controller/request/header
	@GetMapping("controller/request/header")
	@ResponseBody
	public String getControllerRequestHeader() {
		// URL
		final String URL = "http://localhost:8080/server/v1/restcontroller/response/header";
		
		// Template
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY,
				String.class);
		String gelenData = responseEntity.getHeaders().getFirst("key_restcontroller_header");
		String body = responseEntity.getBody();
		
		return "@Controller:" + body + " " + gelenData;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// HEADER
	// Dikkat : Önce @Controller Tıklamalıyız
	// Request ==> @RestController
	// Response ==> @Controller
	// http://localhost:8080/controller/response/header
	@GetMapping("controller/response/header")
	@ResponseBody
	public String getControllerResponseHeader() {
		
		// URL
		final String URL = "http://localhost:8080/server/v1/restcontroller/request/header";
		
		// @RestControllerdan Header verisi
		// Header'a veri ekledik
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("key_controller_header", "@Controller Verileri");
		HttpEntity<String> httpEntity = new HttpEntity<String>("other", httpHeaders);
		
		// Template
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class);
		String body = responseEntity.getBody();
		log.error("@Controller ==> " + body);
		return body;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// COOKE
	// Request ==> @Controller
	// Response ==> @RestController
	// import org.springframework.http.HttpHeaders;
	// http://localhost:8080/controller/request/cookie
	@GetMapping("controller/request/cookie")
	@ResponseBody
	public String getControllerRequestCookie() {
		// URL
		final String URL = "http://localhost:8080/server/v1/restcontroller/response/cookie";
		
		// Template
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY,
				String.class);
		String gelenData = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
		String body = responseEntity.getBody();
		
		return "@Controller:" + body + " " + gelenData;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// COOKIE
	// Dikkat : Önce @Controller Tıklamalıyız
	// Request ==> @RestController
	// Response ==> @Controller
	// http://localhost:8080/controller/response/cookie
	@GetMapping("controller/response/cookie")
	@ResponseBody
	public String getControllerResponseCookie() {
		
		// URL
		final String URL = "http://localhost:8080/server/v1/restcontroller/request/cookie";
		
		// Template
		RestTemplate restTemplate = new RestTemplate();
		
		// @RestControllerdan Header verisi
		// Cookie veri ekledik
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.COOKIE, "key_controller_response_cookie=bencerezim");
		HttpEntity<String> httpEntity = new HttpEntity<String>("other", httpHeaders);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class);
		String body = responseEntity.getBody();
		log.error("@Controller Cookie ==> " + body);
		return "@Controller Cookie ==> " + body;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SECURITY
	// http://localhost:8080/security/public
	// DİKKATTTT: url path oluşturulurken başına slash(/) eklemeliyiz
	@GetMapping("/security/public")
	@ResponseBody
	public String getPublic() {
		return "public Page Welcome";
	}
	
	// SECURITY
	// http://localhost:8080/security/private
	// DİKKATTTT: url path oluşturulurken başına slash(/) eklemeliyiz
	@GetMapping("/security/private")
	@ResponseBody
	public String getPrivate() {
		return "private Page Welcome";
	}
	
	// SECURITY
	// http://localhost:8080/admin/index
	// DİKKATTTT: url path oluşturulurken başına slash(/) eklemeliyiz
	@GetMapping("/admin/index")
	public String getAdmin(Model model) {
		// Sistemdeki Kullanıcı Bilgilerine ulaşmak
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			model.addAttribute("admin_hashCode_key", authentication.hashCode());
			model.addAttribute("admin_name_key", authentication.getName());
			return "admin/index";
		} else {
			return "/";
		}
		
	}
	
	// LOGOUT
	// http://localhost:8080/admin/logout
	// DİKKATTTT: url path oluşturulurken başına slash(/) eklemeliyiz
	// Authentication: import org.springframework.security.core.Authentication;
	@GetMapping("/admin/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response, Model model) {
		// sayfamıza giriş yapmış kişiyi gösterir.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// sistemde birisi varsa
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			model.addAttribute("key_logout", "Çıkış Başarılı");
			return "admin/logout";
		} else {
			model.addAttribute("key_logout", "Başarısız Çıkış");
		}
		return "admin/index";
	}
	
}
