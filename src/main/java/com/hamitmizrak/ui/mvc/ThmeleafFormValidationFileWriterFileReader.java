package com.hamitmizrak.ui.mvc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hamitmizrak.business.dto.CustomerDto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ThmeleafFormValidationFileWriterFileReader {
	
	// object variable
	private static Scanner klavye;
	// class variable
	private static final String MY_PATH = "C:\\turkcell\\spring_form.txt";
	
	// parametresiz constructor
	public ThmeleafFormValidationFileWriterFileReader() {
		klavye = new Scanner(System.in);
	}
	
	// FORM VALIDATION
	// http://localhost:8080/validation/form
	@GetMapping("/validation/form")
	public String validationGetForm(Model model) {
		model.addAttribute("key_validation_form", new CustomerDto());
		return "ThymeleafFormValidationFileWriterFileReader";
	}
	
	// http://localhost:8080/validation/form
	@PostMapping("/validation/form")
	public String validationPostForm(@Valid @ModelAttribute("key_validation_form") CustomerDto customerDto,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			log.error("hata var: " + bindingResult);
			
			return "ThymeleafFormValidationFileWriterFileReader";
		} else {
			model.addAttribute("key_success", CustomerDto.class + " Eklemesi başarılı");
			log.info(customerDto);
			writeDataFile(customerDto);
		}
		// model.addAttribute("key_validation_form", customerDto);
		// hata yoksa success sayfasına gitsin
		return "ThymeleafFormValidationSuccess";
	}
	
	/////////////////////////////////////////////////////
	// write method
	private static void writeDataFile(CustomerDto dto) {
		log.info("Dosyanız" + MY_PATH);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(MY_PATH, true))) {
			bufferedWriter.write(" " + dto);
			bufferedWriter.flush();
			log.info("Dosyanız" + MY_PATH + " yazıldı");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Yazmada sorun oluştu");
		}
	}
	
	// read method
	private static void ReadDataFile() {
		log.info("*** Dosya Oku ***");
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(MY_PATH))) {
			StringBuilder builder = new StringBuilder();
			String satir = "";
			while ((satir = bufferedReader.readLine()) != null) {
				builder.append(satir);
			}
			log.info(builder);
		} catch (Exception e) {
			System.out.println();
			log.error("Okumada sorun oluştu");
			e.printStackTrace();
		}
	}
}
