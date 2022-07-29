package com.hamitmizrak.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerDto {
	
	private Long customerId;
	
	@NotEmpty(message = "müşteri adı boş geçilemez")
	private String customerName;
	
	@NotEmpty(message = "müşteri soyadı boş geçilemez")
	private String customerSurname;
	
	// Hm51524
	@NotEmpty(message = "müşteri şifresi boş geçilemez")
	@Size(min = 7, max = 50, message = "kullanıcı şifresi min:7 max:50 olabilir")
	@Pattern(regexp = "^[a-zA-Z0-9]{7}")
	private String customerPassword;
	
	@NotEmpty(message = "email addres boş geçilemez")
	@Email(message = "uygun formatta girmediniz exam: deneme@xyz.com")
	private String customerEmailAddress;
	
	@NotEmpty(message = "müşteri yaşı boş geçilemez")
	@Min(value = 18, message = "18 yaşından küçük müşteriyi kabul edilmiyor")
	@Max(value = 55, message = "55 yaşından büyük müşteriyi kabul edilmiyor")
	private int customerAge;
	
	@NotEmpty(message = "müşteri messajı boş geçilemez")
	@Size(min = 1, max = 20, message = "1 küçük karakter veya 20 büyük karakter giremezsiniz")
	private String customerMessage;
	
	// +995 111 123456
	@NotEmpty(message = "müşteri telefon no  boş geçilemez")
	@Size(min = 3, max = 15, message = "kullanıcı numarası min:3 max:15 olabilir")
	@Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
	private String customerTelephoneNumber;
	
	// http ServletRequest ServletResponse
	
	// date
	public String getNow = nowDate();
	
	// parametresiz constructor
	public CustomerDto() {
		customerId = 0L;
		customerName = "Müşterini adını yazmadınız";
		this.customerAge = 25;
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
}
