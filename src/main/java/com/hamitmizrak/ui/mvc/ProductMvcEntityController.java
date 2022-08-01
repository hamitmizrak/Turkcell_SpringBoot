package com.hamitmizrak.ui.mvc;

import java.util.Optional;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hamitmizrak.data.entity.ProductEntity;
import com.hamitmizrak.data.repository.IProductRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ProductMvcEntityController {
	
	@Autowired
	IProductRepository repository;
	
	// http://localhost:8080/create/product
	// CREATE
	@GetMapping("/create/product")
	@ResponseBody
	public String createProduct(Model model) {
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
		}
		return "failed";
	}
	
	// SELECT
	// http://localhost:8080/select/product
	@GetMapping("select/product")
	public String selectProduct(Model model) {
		Iterable<ProductEntity> listem = repository.findAll();
		model.addAttribute("entity_key", listem);
		listem.forEach(System.out::println);
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
			model.addAttribute("entity_key", findByIdEntity.get());
		} else {
			model.addAttribute("entity_key", id + " numaralı ID Yoktur.");
			return "failed";
		}
		return "entity_mvc";
	}
	
	// UPDATE
	// http://localhost:8080/update/product/3
	@GetMapping("update/product/{id}")
	public String updateProductById(@PathVariable(name = "id") Long id, Model model) {
		String productName, productCode;
		Optional<ProductEntity> findByIdEntity = repository.findById(id);
		if (findByIdEntity.isPresent()) {
			// ürünü getirmek
			ProductEntity entity = findByIdEntity.get();
			productName = JOptionPane.showInputDialog("Ürün adını giriniz");
			productCode = JOptionPane.showInputDialog("Ürün Kodu girini");
			
			entity.setProductName(productName);
			entity.setProductCode(productCode);
			repository.save(entity);
			model.addAttribute("entity_key", entity);
		} else {
			model.addAttribute("entity_key", id + " numaralı ID Yoktur.");
			return "failed";
		}
		return "entity_mvc";
	}
	
}
