package com.hamitmizrak.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

// lombok
@Data

// entity
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", updatable = false)
	private Long productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_code")
	private String productCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private java.util.Date createdDate;
	
	public ProductEntity() {
		
	}
	
	public ProductEntity(String productName, String productCode) {
		this.productName = productName;
		this.productCode = productCode;
	}
	
}
