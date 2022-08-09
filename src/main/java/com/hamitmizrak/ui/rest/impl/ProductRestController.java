package com.hamitmizrak.ui.rest.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server/v1")
public class ProductRestController {
	
	// http://localhost:8080/server/v1/manueljson
	@GetMapping("manueljson")
	public String sendServerData() {
		return "{\r\n" + "  \"adi\":\"Hamit\",\r\n" + "  \"soyadi\":\"Mızrak\"\r\n" + "}";
	}
	
}
