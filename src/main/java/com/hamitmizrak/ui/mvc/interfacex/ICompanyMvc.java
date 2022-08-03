package com.hamitmizrak.ui.mvc.interfacex;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.hamitmizrak.dto.CompanyDto;

public interface ICompanyMvc {
	
	// CREATE ALL
	public String createAllCompany(Model model);
	
	// CREATE GET
	public String getCreateCompany(Model model);
	
	// CREATE POST
	public String postCreateCompany(CompanyDto companyDto, BindingResult bindingResult);
	
	// LIST
	public String selectCompany(Model model);
	
	// FIND
	public String findCompany(Long id, Model model);
	
	// UPDATE
	public String updateCompanyById(Long companyId, String companyName, String companyLogo, String companyPassword,
			String companyEmailAddress, String companyMessage, String companyTelephoneNumber, int companyTaxNumber,
			String foundationYear, Model model);
	
	// DELETE
	public String deleteCompany(Long id, Model model);
	
	// private String companyName;
	// private String companyLogo;
	// private String companyPassword;
	// private String companyEmailAddress;
	// private int companyTaxNumber;
	// private String companyMessage;
	// private String companyTelephoneNumber;
	// private Date foundationYear;
	
}
