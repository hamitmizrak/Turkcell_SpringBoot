package com.hamitmizrak.ui.rest.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hamitmizrak.business.dto.AdminDto;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1")
@Log4j2
// dış dünyaya açılan kapıdır
public class AdminRestController {
	
	// http://localhost:8080/api/v1/rest/manuelJson
	@GetMapping("rest/manuelJson")
	public String getRest1() {
		return "{\r\n" + "  \"adi\":\"Hamit\",\r\n" + "  \"soyadi\":\"Mızrak\",\r\n" + "  \"sayi\":50,\r\n"
				+ "  \"computer enginerr\":true,\r\n" + "  \"javaee\":[\"servlet\",\"jsp\",\"jsf\"],\r\n"
				+ "  \"java\":{\r\n" + "    \"name\":\"javase\",\r\n" + "    \"version\":\"18\"\r\n" + "  }\r\n" + "}";
	}
	
	// 1.SEÇENEK: jackson dependency eklendiğinde default olan json yerine XML
	// gelir.
	// http://localhost:8080/api/v1/rest/object2
	@GetMapping(value = "rest/object2")
	public AdminDto getRest2() {
		AdminDto adminDto = AdminDto.builder().adminId(0L).adminName("admin adı").adminSurname("admin soyadı").build();
		return adminDto;
	}
	
	// 2.SEÇENEK : jackson dependency eklendiğinde zaten XML geliyor ancak biz
	// işimiz garantiye alıyoruz
	// http://localhost:8080/api/v1/rest/object3
	// import org.springframework.http.MediaType;
	@GetMapping(value = "rest/object3", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminDto getRest3() {
		AdminDto adminDto = AdminDto.builder().adminId(0L).adminName("admin adı").adminSurname("admin soyadı").build();
		return adminDto;
	}
	
	// 3.SEÇENEK (Türkçe karakteri çözmek) ==> UTF-8
	// http://localhost:8080/api/v1/rest/object4
	// import org.springframework.http.MediaType;
	@GetMapping(value = "rest/object4", produces = "application/json;charset=UTF-8")
	public AdminDto getRest4() {
		AdminDto adminDto = AdminDto.builder().adminId(0L).adminName("admin adı").adminSurname("admin soyadı").build();
		return adminDto;
	}
	///////////////////////////////////////////////////////////////////////
	
	// http://localhost:8080/api/v1/rest/object5/5
	@GetMapping("rest/object5/{id}")
	public AdminDto getRest5(@PathVariable(name = "id", required = false) Long id) {
		AdminDto adminDto = AdminDto.builder().adminId(id).adminName("admin adı").adminSurname("admin soyadı").build();
		return adminDto;
	}
	
	// http://localhost:8080/api/v1/rest/object6
	// http://localhost:8080/api/v1/rest/object6/6
	@GetMapping({ "rest/object6", "rest/object6/{id}" })
	public AdminDto getRest6(@PathVariable(name = "id", required = false) Long id) {
		AdminDto adminDto = null;
		if (id == null) {
			log.error("id null verilmiştir");
		} else {
			adminDto = AdminDto.builder().adminId(id).adminName("admin adı").adminSurname("admin soyadı").build();
		}
		return adminDto;
	}
	
	///////////////////////////////////////////////////////////////////////
	
	// http://localhost:8080/api/v1/rest/object7?id=4
	@GetMapping("rest/object7")
	public AdminDto getRest7(@RequestParam(name = "id") Long id) {
		AdminDto adminDto = null;
		if (id == null) {
			log.error("id null verilmiştir");
		} else {
			adminDto = AdminDto.builder().adminId(id).adminName("admin adı").adminSurname("admin soyadı").build();
		}
		return adminDto;
	}
	
	///////////////////////////////////////////////////////////////////////
	
	// servlet secret
	@Autowired
	private ServletContext servletContext;
	
	// application.properties gizli bilgi almak
	// http://localhost:8080/api/v1/rest/object8
	@GetMapping("rest/object8")
	@ResponseBody
	public String getRest8() {
		String secretInformation = servletContext.getInitParameter("benimsifrem");
		log.info(secretInformation);
		return secretInformation;
	}
	
	// application.properties gizli bilgi almak
	// http://localhost:8080/api/v1/rest/object9
	@GetMapping("rest/object9")
	@ResponseBody
	public ResponseEntity<?> getRest9() {
		String secretInformation = servletContext.getInitParameter("benimsifrem");
		log.info(secretInformation);
		return ResponseEntity.ok(secretInformation);
	}
	
	// servlet request response
	// http://localhost:8080/api/v1/rest/object10
	@GetMapping("rest/object10")
	@ResponseBody
	public ResponseEntity<?> getRest10(HttpServletRequest request, HttpServletResponse response) {
		
		// URI
		String uri = request.getRequestURI();
		log.info(uri);
		
		// LocalAddress
		String localAddress = request.getLocalAddr();
		log.info(localAddress);
		
		// Session
		String session = request.getSession().toString();
		log.info(session);
		
		StringBuilder builder = new StringBuilder();
		builder.append("uri: " + uri).append(" session: " + session).append(" localAddress: " + localAddress);
		String changeTo = builder.toString();
		
		return ResponseEntity.ok(changeTo);
	}
	
}
