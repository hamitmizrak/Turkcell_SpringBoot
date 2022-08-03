package com.hamitmizrak.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "company")
public class CompanyEntity extends BaseEntity implements Serializable {
	// serialVersionUID
	private static final long serialVersionUID = 1L;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_logo")
	private String companyLogo;
	
	@Column(name = "company_password")
	private String companyPassword;
	
	@Column(name = "company_email")
	private String companyEmailAddress;
	
	@Column(name = "company_message")
	private String companyMessage;
	
	@Column(name = "company_telephone_number")
	private String companyTelephoneNumber;
	
	@Column(name = "company_foundation_year")
	private String foundationYear;
	
	@Transient
	private String manuelData;
	
	// unique olmasını kaldırdım
	@Column(name = "company_tax_number", nullable = false, length = 40, updatable = false, unique = false)
	private int companyTaxNumber;
	
	// private Long id;
	// private String companyName;
	// private String companyLogo;
	// private String companyPassword;
	// private String companyEmailAddress;
	// private int companyTaxNumber;
	// private String companyMessage;
	// private String companyTelephoneNumber;
	// private Date foundationYear;
}
