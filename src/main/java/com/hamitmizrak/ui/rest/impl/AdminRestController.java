package com.hamitmizrak.ui.rest.impl;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
}
