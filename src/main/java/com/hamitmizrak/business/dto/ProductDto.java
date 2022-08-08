package com.hamitmizrak.business.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
	
	private Long productId;
	private String productName;
	private String productCode;
	private double productPrice;
	// private String productTrade;
	
	// parametresiz contructor
	public ProductDto() {
		this.productId = 0L;
		this.productName = "ürün adını girmediniz";
		this.productCode = "ürün codunu girmediniz";
		this.productPrice = 0;
	}
	
	// parametreli constructor
	public ProductDto(Long productId, String productName, String productCode, double productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productCode = productCode;
		this.productPrice = productPrice;
	}
}
