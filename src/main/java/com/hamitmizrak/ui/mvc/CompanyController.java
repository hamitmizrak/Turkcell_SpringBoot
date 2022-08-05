package com.hamitmizrak.ui.mvc;

import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hamitmizrak.data.entity.CompanyEntity;
import com.hamitmizrak.data.repository.ICompanyRepository;
import com.hamitmizrak.dto.CompanyDto;
import com.hamitmizrak.ui.mvc.interfacex.ICompanyMvc;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
// org.springframework.transaction.annotation.Transactional;
// class düzeyinde transactional olsun
@Transactional
public class CompanyController implements ICompanyMvc {
	
	// Inject
	@Autowired
	ICompanyRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	// Random 10<=TAX<=1000
	private int randomNumber() {
		Random random = new Random();
		int tax = random.nextInt(90) + 10;
		System.out.println(tax);
		return tax;
	}
	
	// http://localhost:8080/company/data/all
	// otomatik 10 data eklensin
	@Override
	@GetMapping("company/data/all")
	@ResponseBody
	public String createAllCompany(Model model) {
		int counter = 0;
		for (int i = 1; i <= 10; i++) {
			CompanyEntity companyEntity = CompanyEntity.builder().companyEmailAddress("email adres" + i)
					.companyLogo("Logo" + i).companyMessage("Message" + i).companyName("company name" + i)
					.companyPassword("şifre" + i).companyTelephoneNumber("Telefon" + i).companyTaxNumber(randomNumber())
					.build();
			repository.save(companyEntity);
			counter++;
		}
		return counter + " tane Company oluşturuldu";
	}
	
	// CREATE GET
	// http://localhost:8080/save/company
	@GetMapping("save/company")
	@Override
	public String getCreateCompany(Model model) {
		model.addAttribute("company_key", new CompanyDto());
		return "company_create";
	}
	
	// CREATE POST
	// http://localhost:8080/save/company
	@PostMapping("save/company")
	@Override
	public String postCreateCompany(@Valid @ModelAttribute("company_key") CompanyDto companyDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error(bindingResult);
			return "company_create";
		}
		// ModelMapper
		CompanyEntity entity = modelMapper.map(companyDto, CompanyEntity.class);
		repository.save(entity);
		return "redirect:/list/company";
	}
	
	// LIST
	// http://localhost:8080/list/company
	@Override
	@GetMapping("list/company")
	public String selectCompany(Model model) {
		Iterable<CompanyEntity> listem = repository.findAll();
		model.addAttribute("company_key", listem);
		listem.forEach(System.out::println);
		return "company_list";
	}
	
	// FIND
	// http://localhost:8080/find/company
	// http://localhost:8080/find/company/1
	@Override
	@GetMapping({ "find/company", "find/company/{id}" })
	public String findCompany(@PathVariable(name = "id", required = false) Long id, Model model) {
		Optional<CompanyEntity> findData = repository.findById(id);
		if (findData.isPresent()) {
			model.addAttribute("company_key", findData.get());
			return "company_detail_page";
		} else {
			model.addAttribute("company_not_found", id + " ID data yoktur");
		}
		return "redirect:/list/company";
	}
	
	// UPDATE GET
	@Override
	@GetMapping("update/company/{id}")
	public String updateCompanyUpdateGetForm(@PathVariable(name = "id") Long id, Model model) {
		Optional<CompanyEntity> findData = repository.findById(id);
		if (findData.isPresent()) {
			model.addAttribute("company_update", findData.get());
			return "company_update";
		} else {
			model.addAttribute("company_not_found", id + " ID data yoktur");
		}
		return "redirect:/list/company";
	}
	
	// UPDATE POST
	@Override
	@PostMapping("update/company/{id}")
	public String updateCompanyUpdatePostForm(@Valid @ModelAttribute("company_update") CompanyDto companyDto,
			@PathVariable(name = "id") Long id, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error(bindingResult);
			return "company_update";
		}
		// ModelMapper
		CompanyEntity entity = modelMapper.map(companyDto, CompanyEntity.class);
		repository.save(entity);
		return "redirect:/list/company";
	}
	
	// DELETE
	// http://localhost:8080/delete/company/1
	@Override
	@GetMapping("delete/company/{id}")
	public String deleteCompany(@PathVariable(name = "id") Long id, Model model) {
		Optional<CompanyEntity> findByIdEntity = repository.findById(id);
		if (findByIdEntity.isPresent()) {
			repository.deleteById(id);
			log.info(findByIdEntity.get());
			// model.addAttribute("entity_key", findByIdEntity.get());
			model.addAttribute("company_not_found", "ID: " + findByIdEntity.get().getId() + " Silindi.");
		} else {
			model.addAttribute("company_not_found", id + " numaralı ID Yoktur.");
		}
		return "redirect:/list/company";
	}
	
	/////////////////////////////////////////////////////////////////
	// Sorting:Küçükten Büyüğe , Büyükten Küçüğe
	// http://localhost:8080/company/sorting/asc
	@GetMapping("company/sorting/asc")
	@ResponseBody
	public String companySortingAsc() {
		// import org.springframework.data.domain.Sort;
		// Dikkat: Entity attributes alıyoruz.
		// Sort sort = Sort.by("companyName");
		Sort sort = Sort.by("companyName").ascending();
		Iterable<CompanyEntity> sortingList = repository.findAll(sort);
		for (CompanyEntity temp : sortingList) {
			log.info(temp);
		}
		return sortingList + " ";
	}
	
	// Sorting: Büyükten Küçüğe Doğru sıralama
	// http://localhost:8080/company/sorting/desc
	@GetMapping("company/sorting/desc")
	@ResponseBody
	public String companySortingDesc() {
		// import org.springframework.data.domain.Sort;
		// Dikkat: Entity attributes alıyoruz.
		Sort sort = Sort.by("companyName").descending();
		Iterable<CompanyEntity> sortingList = repository.findAll(sort);
		for (CompanyEntity temp : sortingList) {
			log.info(temp);
		}
		return sortingList + " ";
	}
	
	/////////////////////////////////////////////////////////////////
	
	// Paging:Sayfalama
	// 100 tane datayı kaçarlı sayfalara bölelim?
	// http://localhost:8080/company/paging/0/10
	@GetMapping("company/paging/{page}/{size}")
	@ResponseBody
	public String companyPaging(@PathVariable(name = "page") int page, @PathVariable(name = "size") int size) {
		// import org.springframework.data.domain.Sort;
		// Dikkat: Entity attributes alıyoruz.
		// Dikkat: org.thymeleaf.exceptions.TemplateInputException: burada @ResponseBody
		// yazalım veya uygun html yapalım
		Pageable pageable = PageRequest.of(page, size);
		Page<CompanyEntity> pagingList = repository.findAll(pageable);
		for (CompanyEntity temp : pagingList) {
			log.info(temp);
		}
		return pagingList + " ";
	}
	
	/////////////////////////////////////////////////////////////////
	// Paging Sorting
	// http://localhost:8080/company/paging/sorting/0/10
	@GetMapping("company/paging/sorting/{page}/{size}")
	@ResponseBody
	public String companyPagingAndSorting(@PathVariable(name = "page") int page,
			@PathVariable(name = "size") int size) {
		// import org.springframework.data.domain.Sort;
		// Dikkat: Entity attributes alıyoruz.
		// Dikkat: org.thymeleaf.exceptions.TemplateInputException: burada @ResponseBody
		// yazalım veya uygun html yapalım
		
		Sort sort = Sort.by("companyName").ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<CompanyEntity> pagingList = repository.findAll(pageable);
		for (CompanyEntity temp : pagingList) {
			log.info(temp);
		}
		return pagingList + " ";
	}
	
	/////////////////////////////////////////////////////////////////
	
	/////// #### Delived Query ####
	// Bizim yazdığımız sorgu
	// http://localhost:8080/find/company/delivedquery/hamit%209
	@GetMapping("find/company/delivedquery/{company_name}")
	// @ResponseBody
	public String findProductName(@PathVariable(name = "company_name") String companyName, Model model) {
		//// http://localhost:8080/find/company/delivedquer/hamit 9
		// List<CompanyEntity> listem =
		// repository.findProductEntitiesByProductName(productName);
		// List<CompanyEntity> listem = repository.findByProductName(productName);
		// model.addAttribute("entity_key", listem);
		
		//// starts
		//// http://localhost:8080/find/company/delivedquer/h
		// List<CompanyEntity> listem =
		// repository.findByProductNameStartingWith(productName);
		// List<CompanyEntity> listem =
		// repository.findByProductNameStartsWith(productName);
		// model.addAttribute("entity_key", listem);
		
		//// ends
		//// http://localhost:8080/find/company/delivedquer/1
		// List<CompanyEntity> listem =
		// repository.findByProductNameEndsWith(productName);
		// model.addAttribute("entity_key", listem);
		
		//// equals
		//// http://localhost:8080/find/company/delivedquer/hamit 1
		//// http://localhost:8080/find/company/delivedquer/hamit%201
		// List<CompanyEntity> listem = repository.findByProductNameEquals(productName);
		// List<CompanyEntity> listem = repository.findByProductNameIs(productName);
		// model.addAttribute("entity_key", listem);
		
		//// not
		//// http://localhost:8080/find/company/delivedquer/hamit 1
		//// http://localhost:8080/find/company/delivedquer/hamit%201
		// List<CompanyEntity> listem = repository.findByProductNameNot(productName);
		// List<CompanyEntity> listem = repository.findByProductNameIsNot(productName);
		// model.addAttribute("entity_key", listem);
		
		//// distinct: tekrar etmeyen listelemek
		// http://localhost:8080/find/company/delivedquer/hamit 1
		// List<CompanyEntity> listem =
		// repository.findDistinctByProductName(productName);
		// model.addAttribute("entity_key", listem);
		
		//// like
		//// http://localhost:8080/find/company/delivedquer/hamit
		//// başlayan
		// List<CompanyEntity> listem = repository.findByProductNameLike( productName +
		// "%");
		//// içinde geçen
		// List<CompanyEntity> listem = repository.findByProductNameLike("%" +
		// productName + "%");
		//// biten
		// List<CompanyEntity> listem = repository.findByProductNameLike("%" +
		// productName);
		// model.addAttribute("entity_key", listem);
		
		//// productPrice
		//// productPrice:200 olanları bana listele
		//// http://localhost:8080/find/company/delivedquer/hamit
		// List<CompanyEntity> listem =
		// repository.findByProductPrice(Double.valueOf(200));
		// model.addAttribute("entity_key", listem);
		
		//// GreaterThan: verilen sayıdadan büyük olanları
		//// http://localhost:8080/find/company/delivedquer/hamit
		// List<CompanyEntity> listem = repository.findByProductPriceGreaterThan(300);
		// model.addAttribute("entity_key", listem);
		
		//// Beetween
		//// http://localhost:8080/find/company/delivedquer/hamit
		// List<CompanyEntity> listem = repository.findByProductPriceBetween(200, 500);
		// model.addAttribute("entity_key", listem);
		
		//// Çoklu arama
		//// http://localhost:8080/find/company/delivedquer/hamit 1
		//// http://localhost:8080/find/company/delivedquer/hamit%201
		// List<CompanyEntity> listem =
		// repository.findByProductNameOrProductCodeAllIgnoreCase("hamit 1",
		// "a20953df-120a-4203-989b-1c77096122fa");
		// model.addAttribute("entity_key", listem);
		
		//// order by sıralama: küçükten büyüğe sıralamak
		//// http://localhost:8080/find/company/delivedquer/hamit
		// List<CompanyEntity> listem =
		// repository.findByProductNameContainingOrderByProductName(productName);
		// model.addAttribute("entity_key", listem);
		
		//// order by sıralama: büyükten küçüğe sıralamak
		//// http://localhost:8080/find/company/delivedquer/hamit
		// List<CompanyEntity> listem =
		// repository.findByProductNameContainingOrderByProductNameDesc(productName);
		// model.addAttribute("entity_key", listem);
		
		//// limit
		//// http://localhost:8080/find/company/delivedquer/hamit
		// Tepe 1
		// List<CompanyEntity> listem = repository.findFirstByOrderById();
		// List<CompanyEntity> listem = repository.findTopByOrderByProductName();
		// List<CompanyEntity> listem = repository.findFirst4ByOrderById(); //
		// model.addAttribute("entity_key", listem);
		
		//// delete
		//// http://localhost:8080/find/company/delivedquer/hamit
		// repository.deleteById(Long.valueOf(1)); //
		
		//// null not
		//// http://localhost:8080/find/company/delivedquer/hamit 1
		//// http://localhost:8080/find/company/delivedquer/hamit%201
		// List<CompanyEntity> listem = repository.findByProductNameIsNull(productName);
		// List<CompanyEntity> listem = repository.findByProductNameIsNot(productName);
		// model.addAttribute("entity_key", listem);
		
		//// count
		//// http://localhost:8080/find/company/delivedquer/hamit 2
		//// http://localhost:8080/find/company/delivedquer/hamit%201
		// int countName = repository.countByProductName(productName);
		// model.addAttribute("entity_count_key", countName);
		
		return "entity_mvc";
	}
	
}
