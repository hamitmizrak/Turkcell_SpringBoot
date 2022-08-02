package com.hamitmizrak.ui.mvc;

import java.util.List;
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
			entity = new ProductEntity("hamit " + i, uuid.toString(), i * 100);
			repository.save(entity);
			counter++;
		}
		model.addAttribute("entity_key", entity);
		return counter + " tane Product Eklendi";
	}
	
	// CREATE
	// http://localhost:8080/create/product?product_name=ürünadi&product_code=ürünkodu&product_price=100
	@GetMapping("/create/product")
	public String createProduct(@RequestParam(name = "product_name") String productName,
			@RequestParam(name = "product_code") String productCode,
			@RequestParam(name = "product_price") double productPrice, Model model) {
		ProductEntity entity = new ProductEntity(productName, productCode, productPrice);
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
	// http://localhost:8080/update/product?product_id=9&product_name=ürünadi44&product_code=ürünkodu44&product_price=100
	@GetMapping("update/product")
	public String updateProductById(@RequestParam(name = "product_id") Long productId,
			@RequestParam(name = "product_name") String productName,
			@RequestParam(name = "product_code") String productCode,
			@RequestParam(name = "product_price") double productPrice, Model model) {
		
		Optional<ProductEntity> findByIdEntity = repository.findById(productId);
		if (findByIdEntity.isPresent()) {
			// ürünü getirmek
			ProductEntity entity = findByIdEntity.get();
			entity.setProductName(productName);
			entity.setProductCode(productCode);
			entity.setProductPrice(productPrice);
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
	
	/////// #### Delived Query ####
	// Bizim yazdığımız sorgu
	// http://localhost:8080/find/productdata/hamit%209
	@GetMapping("find/productdata/{product_name}")
	// @ResponseBody
	public String findProductName(@PathVariable(name = "product_name") String productName, Model model) {
		// http://localhost:8080/find/productdata/hamit 9
		// List<ProductEntity> listem =
		// repository.findProductEntitiesByProductName(productName);
		// List<ProductEntity> listem = repository.findByProductName(productName);
		// model.addAttribute("entity_key", listem);
		
		// starts
		// http://localhost:8080/find/productdata/h
		// List<ProductEntity> listem =
		// repository.findByProductNameStartingWith(productName);
		// List<ProductEntity> listem =
		// repository.findByProductNameStartsWith(productName);
		// model.addAttribute("entity_key", listem);
		
		// ends
		// http://localhost:8080/find/productdata/1
		// List<ProductEntity> listem =
		// repository.findByProductNameEndsWith(productName);
		// model.addAttribute("entity_key", listem);
		
		// equals
		// http://localhost:8080/find/productdata/hamit 1
		// http://localhost:8080/find/productdata/hamit%201
		// List<ProductEntity> listem = repository.findByProductNameEquals(productName);
		// List<ProductEntity> listem = repository.findByProductNameIs(productName);
		// model.addAttribute("entity_key", listem);
		
		// not
		// http://localhost:8080/find/productdata/hamit 1
		// http://localhost:8080/find/productdata/hamit%201
		// List<ProductEntity> listem = repository.findByProductNameNot(productName);
		// List<ProductEntity> listem = repository.findByProductNameIsNot(productName);
		// model.addAttribute("entity_key", listem);
		
		// distinct: tekrar etmeyen listelemek
		// http://localhost:8080/find/productdata/hamit 1
		// List<ProductEntity> listem =
		// repository.findDistinctByProductName(productName);
		// model.addAttribute("entity_key", listem);
		
		// like
		// http://localhost:8080/find/productdata/hamit
		// başlayan
		// List<ProductEntity> listem = repository.findByProductNameLike( productName +
		// "%");
		// içinde geçen
		// List<ProductEntity> listem = repository.findByProductNameLike("%" +
		// productName + "%");
		// biten
		// List<ProductEntity> listem = repository.findByProductNameLike("%" +
		// productName);
		// model.addAttribute("entity_key", listem);
		
		// productPrice
		// productPrice:200 olanları bana listele
		// http://localhost:8080/find/productdata/hamit
		// List<ProductEntity> listem =
		// repository.findByProductPrice(Double.valueOf(200));
		// model.addAttribute("entity_key", listem);
		
		// GreaterThan: verilen sayıdadan büyük olanları
		// http://localhost:8080/find/productdata/hamit
		// List<ProductEntity> listem = repository.findByProductPriceGreaterThan(300);
		// model.addAttribute("entity_key", listem);
		
		// Beetween
		// http://localhost:8080/find/productdata/hamit
		List<ProductEntity> listem = repository.findByProductPriceBetween(200, 500);
		model.addAttribute("entity_key", listem);
		
		// Çoklu arama
		// http://localhost:8080/find/productdata/hamit 1
		// http://localhost:8080/find/productdata/hamit%201
		// List<ProductEntity> listem =
		// repository.findByProductNameOrProductCodeAllIgnoreCase("hamit 1",
		// "a20953df-120a-4203-989b-1c77096122fa");
		// model.addAttribute("entity_key", listem);
		
		// order by sıralama: küçükten büyüğe sıralamak
		// http://localhost:8080/find/productdata/hamit
		// List<ProductEntity> listem =
		// repository.findByProductNameContainingOrderByProductName(productName);
		// model.addAttribute("entity_key", listem);
		
		// order by sıralama: büyükten küçüğe sıralamak
		// http://localhost:8080/find/productdata/hamit
		// List<ProductEntity> listem =
		// repository.findByProductNameContainingOrderByProductNameDesc(productName);
		// model.addAttribute("entity_key", listem);
		
		// limit
		// http://localhost:8080/find/productdata/hamit
		// Tepe 1
		// List<ProductEntity> listem = repository.findFirstByOrderById();
		// List<ProductEntity> listem = repository.findTopByOrderByProductName();
		// List<ProductEntity> listem = repository.findFirst4ByOrderById(); //
		// model.addAttribute("entity_key", listem);
		
		// delete
		// http://localhost:8080/find/productdata/hamit
		// repository.deleteById(Long.valueOf(1)); //
		
		// null not
		// http://localhost:8080/find/productdata/hamit 1
		// http://localhost:8080/find/productdata/hamit%201
		// List<ProductEntity> listem = repository.findByProductNameIsNull(productName);
		// List<ProductEntity> listem = repository.findByProductNameIsNot(productName);
		// model.addAttribute("entity_key", listem);
		
		// count
		// http://localhost:8080/find/productdata/hamit 2
		// http://localhost:8080/find/productdata/hamit%201
		// int countName = repository.countByProductName(productName);
		// model.addAttribute("entity_count_key", countName);
		
		return "entity_mvc";
	}
	
}
