package com.hamitmizrak.ui.mvc;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hamitmizrak.data.entity.ProductEntity;
import com.hamitmizrak.data.repository.IProductRepository;

@Controller
public class ProductMvcEntityController {
	
	@Autowired
	IProductRepository repository;
	
	// http://localhost:8080/product/entity
	@GetMapping("/product/entity")
	@ResponseBody
	public String mvcEntityMethod(Model model) {
		UUID uuid = UUID.randomUUID();
		ProductEntity entity = new ProductEntity("ürün adı44", uuid.toString());
		model.addAttribute("entity_key", entity);
		repository.save(entity);
		return "Product Eklend";
	}
	
}
