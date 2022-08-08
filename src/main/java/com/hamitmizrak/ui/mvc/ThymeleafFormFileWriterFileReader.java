package com.hamitmizrak.ui.mvc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hamitmizrak.business.dto.ProductDto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ThymeleafFormFileWriterFileReader {
	private static Scanner klavye;
	private static final String MY_PATH = "C:\\turkcell\\spring_form.txt";
	
	public ThymeleafFormFileWriterFileReader() {
		klavye = new Scanner(System.in);
	}
	
	// http://localhost:8080/product/form
	// default html sayfasınının ilk açıldığı zaman görünecek
	@GetMapping("/product/form")
	public String getForm(Model model) {
		model.addAttribute("key_form", new ProductDto());
		return "form";
	}
	
	// http://localhost:8080/product/form
	@PostMapping("/product/form")
	public String postForm(Model model, ProductDto productDto) {
		model.addAttribute("key_form", productDto);
		log.info(productDto);
		// save
		// file upload
		writeDataFile(productDto);
		// file reader
		ReadDataFile();
		return "form";
	}
	
	/////// write method
	private static void writeDataFile(ProductDto dto) {
		System.out.println(MY_PATH);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(MY_PATH, true))) {
			bufferedWriter.write(dto.getProductId() + " " + dto.getProductName() + " " + dto.getProductCode());
			bufferedWriter.flush();
			log.info("Dosyanız" + MY_PATH + " yazıldı");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Yazmada sorun oluştu");
		}
	}
	
	//////// read method
	private static void ReadDataFile() {
		System.out.println("*** Dosya Oku ***");
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
