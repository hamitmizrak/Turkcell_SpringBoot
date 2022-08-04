package com.hamitmizrak.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {
	
	private Long id;
	
	@NotEmpty(message = "Şirket adı boş geçilemez")
	private String companyName;
	
	@NotEmpty(message = "Şirket Logo boş geçilemez")
	private String companyLogo;
	
	// Hm51524
	@NotEmpty(message = "Şirket şifresi boş geçilemez")
	@Size(min = 7, max = 50, message = "kullanıcı şifresi min:7 max:50 olabilir")
	@Pattern(regexp = "^[a-zA-Z0-9]{7}")
	private String companyPassword;
	
	@NotEmpty(message = "email addres boş geçilemez")
	@Email(message = "uygun formatta girmediniz exam: deneme@xyz.com")
	private String companyEmailAddress;
	
	@Min(value = 10, message = "10 küçük vergi numarası olamaz")
	@Max(value = 100, message = "100 büyük vergi numarası olamaz")
	private int companyTaxNumber = 10;
	
	@NotEmpty(message = "Şirket messajı boş geçilemez")
	@Size(min = 1, max = 20, message = "1 küçük karakter veya 20 büyük karakter giremezsiniz")
	private String companyMessage;
	
	// +995 111 123456
	@NotEmpty(message = "Şirket telefon no  boş geçilemez")
	@Size(min = 3, max = 15, message = "kullanıcı numarası min:3 max:15 olabilir")
	@Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
	private String companyTelephoneNumber;
	
	@NotEmpty(message = "Şirket kuruluş yılı boş bırakılamaz")
	private String foundationYear;
	
	// date
	private String creationDateManuel = getNowDate();
	
	public String getNowDate() {
		Locale locale = new Locale("tr", "TR");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss", locale);
		// String change = formatter.format(new
		// java.util.Date(System.currentTimeMillis()));
		String change = formatter.format(new java.util.Date());
		return change;
	}
}
