package com.hamitmizrak.myrepository;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok
@Getter
@Setter
@Builder
@ToString

// Entity
@Entity
@Table(name = "computer")
public class ComputerEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "computerd_id")
	private Long computerId;
	
	@Column(name = "computer_name")
	private String computerName;
	
	@Column(name = "computer_trade")
	private String computerTrade;
	
	@Column(name = "computer_price", columnDefinition = "Decimal(10,2) default '100.00'")
	private double computerPrice;
	
	@Lob
	private String image;
	
	@Transient
	private String justJavaVariable;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	// parametresiz constructor
	public ComputerEntity() {
	}
	
	// parametreli constructor
	public ComputerEntity(Long computerId, String computerName, String computerTrade, double computerPrice,
			String image, String justJavaVariable, Date createdDate) {
		
		this.computerId = computerId;
		this.computerName = computerName;
		this.computerTrade = computerTrade;
		this.computerPrice = computerPrice;
		this.image = image;
		this.justJavaVariable = justJavaVariable;
		this.createdDate = createdDate;
	}
	
}
