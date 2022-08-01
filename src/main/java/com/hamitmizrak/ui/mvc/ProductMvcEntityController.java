package com.hamitmizrak.ui.mvc;

import java.util.UUID;

import com.hamitmizrak.data.entity.ProductEntity;
import com.hamitmizrak.data.repository.IProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductMvcEntityController {
	
	@Autowired
	IProductRepository repository;
	
	// http://localhost:8080/product/entity
	@GetMapping("/product/entity")
	public String mvcEntityMethod(Model model) {
		UUID uuid = UUID.randomUUID();
		ProductEntity entity = new ProductEntity("ürün adı44", uuid.toString());
		model.addAttribute("entity_key", entity);
		repository.save(entity);
		return "entity_mvc";
	}
	
}
