package com.hamitmizrak.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerDto {
	
	private Long customerId;
	
	@NotEmpty(message = "müşteri adı boş geçilemez")
	private String customerName;
	
	@NotEmpty(message = "müşteri soyadı boş geçilemez")
	private String customerSurname;
	
	@NotEmpty(message = "email addres boş geçilemez")
	@Email(message = "uygun formatta girmediniz exam: deneme@xyz.com")
	private String emailAddress;
	
	@NotEmpty(message = "müşteri yaşı boş geçilemez")
	@Min(value = 18, message = "18 yaşından küçük müşteriyi kabul edilmiyor")
	@Max(value = 55, message = "55 yaşından büyük müşteriyi kabul edilmiyor")
	private int customerAge;
	
	@NotEmpty(message = "müşteri messajı boş geçilemez")
	@Size(min = 1, max = 20, message = "1 küçük karakter veya 20 büyük karakter giremezsiniz")
	private String customerMessage;
	
	@NotEmpty(message = "müşteri telefon no  boş geçilemez")
	private String telephoneNumber;
	
	// date
	public String getNow = nowDate();
	
	// parametresiz constructor
	public CustomerDto() {
		customerId = 0L;
		customerName = "Müşterini adını yazmadınız";
		customerSurname = "Müşterini soyadını yazmadınız";
		this.emailAddress = "email addres yazmadınız";
	}
	
	// parametreli constructor (id'siz)
	public CustomerDto(String customerName, String customerSurname, String emailAddress) {
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.emailAddress = emailAddress;
	}
	
	// parametreli constructor (id'li)
	public CustomerDto(Long customerId, String customerName, String customerSurname, String emailAddress) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.emailAddress = emailAddress;
	}
	
	// now Date
	private static String nowDate() {
		Locale locale = new Locale("tr", "TR");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss", locale);
		Date date = new Date();
		String changeString = simpleDateFormat.format(date);
		System.out.println(changeString);
		return changeString;
	}
	
	public static void main(String[] args) {
		nowDate();
	}
	
}
