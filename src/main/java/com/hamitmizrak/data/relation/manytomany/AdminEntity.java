package com.hamitmizrak.data.relation.manytomany;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

// lombok
@Getter
@Setter

// Entity
@Entity
@Table(name = "admin")
// N(Admin) N(Roles)
public class AdminEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private Long adminId;
	
	@Column(name = "admin_name")
	private String adminName;
	
	@Column(name = "admin_email_address")
	private String adminEmailAddress;
	
	@Column(name = "admin_password")
	private String adminPassword;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	// Relation
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "admin_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private Collection<RolesEntity> rolesEntities;
	
	// parametresiz constructor
	public AdminEntity() {
	}
	
	// parametreli constructor
	public AdminEntity(Long adminId, String adminName, String adminEmailAddress, String adminPassword,
			Date createdDate) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminEmailAddress = adminEmailAddress;
		this.adminPassword = adminPassword;
		this.createdDate = createdDate;
	}
	
}
