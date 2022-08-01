package com.hamitmizrak.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok
@Getter
@Setter
@ToString

// entity
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_code", unique = true, nullable = false)
	private String productCode;
	
	// parametresiz constructor
	public ProductEntity() {
	}
	
	// parametreli constructor
	public ProductEntity(String productName, String productCode) {
		this.productName = productName;
		this.productCode = productCode;
	}
	
}
