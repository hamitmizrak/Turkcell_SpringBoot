package com.hamitmizrak.ui.mvc;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hamitmizrak.data.entity.ProductEntity;
import com.hamitmizrak.data.repository.IProductRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ProductMvcEntityController {
	
	@Autowired
	IProductRepository repository;
	
	// CREATE ALL
	// http://localhost:8080/create/all/product
	@GetMapping("/create/all/product")
	@ResponseBody
	public String createAllProduct(Model model) {
		ProductEntity entity = null;
		int counter = 1;
		for (int i = 1; i < 10; i++) {
			UUID uuid = UUID.randomUUID();
			entity = new ProductEntity("ürün adı: " + i, uuid.toString());
			repository.save(entity);
			counter++;
		}
		model.addAttribute("entity_key", entity);
		return counter + " tane Product Eklendi";
	}
	
	// CREATE
	// http://localhost:8080/create/product?product_name=ürünadi&product_code=ürünkodu
	@GetMapping("/create/product")
	public String createProduct(@RequestParam(name = "product_name") String productName,
			@RequestParam(name = "product_code") String productCode, Model model) {
		ProductEntity entity = new ProductEntity(productName, productCode);
		model.addAttribute("entity_key", entity);
		log.info(entity);
		repository.save(entity);
		return "entity_mvc";
	}
	
	// LIST
	// http://localhost:8080/select/product
	@GetMapping("select/product")
	public String selectProduct(Model model) {
		Iterable<ProductEntity> listem = repository.findAll();
		model.addAttribute("entity_key", listem);
		listem.forEach(System.out::println);
		return "entity_mvc";
	}
	
	// NOT: findById,deleteById,update ==> bize ID lazım
	// FIND
	// http://localhost:8080/find/product/1
	@GetMapping("find/product/{id}")
	// @ResponseBody
	public String findProduct(@PathVariable(name = "id") Long id, Model model) {
		Optional<ProductEntity> findEntity = repository.findById(id);
		if (findEntity.isPresent()) {
			model.addAttribute("entity_key", findEntity.get());
			log.info(findEntity.get());
			return "entity_mvc";
		} else {
			model.addAttribute("entity_not_key", id + " numaralı ID Yoktur.");
		}
		// return "failed";
		return "entity_mvc";
	}
	
	// UPDATE
	// http://localhost:8080/update/product?product_id=9&product_name=ürünadi44&product_code=ürünkodu44
	@GetMapping("update/product")
	public String updateProductById(@RequestParam(name = "product_id") Long productId,
			@RequestParam(name = "product_name") String productName,
			@RequestParam(name = "product_code") String productCode, Model model) {
		
		Optional<ProductEntity> findByIdEntity = repository.findById(productId);
		if (findByIdEntity.isPresent()) {
			// ürünü getirmek
			ProductEntity entity = findByIdEntity.get();
			entity.setProductName(productName);
			entity.setProductCode(productCode);
			repository.save(entity);
			model.addAttribute("entity_key", entity);
		} else {
			model.addAttribute("entity_not_key", productId + " numaralı ID Yoktur.");
		}
		return "entity_mvc";
	}
	
	// DELETE
	// http://localhost:8080/delete/product/1
	@GetMapping("delete/product/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id, Model model) {
		Optional<ProductEntity> findByIdEntity = repository.findById(id);
		if (findByIdEntity.isPresent()) {
			repository.deleteById(id);
			log.info(findByIdEntity.get());
			// model.addAttribute("entity_key", findByIdEntity.get());
			model.addAttribute("entity_not_key", "ID: " + findByIdEntity.get().getId() + " Silindi.");
		} else {
			model.addAttribute("entity_not_key", id + " numaralı ID Yoktur.");
		}
		return "entity_mvc";
	}
	
}
