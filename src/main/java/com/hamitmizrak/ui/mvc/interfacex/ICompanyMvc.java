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
	public String updateCompanyUpdateGetForm(Long id, Model model);
	
	public String updateCompanyUpdatePostForm(CompanyDto companyDto, Long id, BindingResult bindingResult);
	
	// DELETE
	public String deleteCompany(Long id, Model model);
}
