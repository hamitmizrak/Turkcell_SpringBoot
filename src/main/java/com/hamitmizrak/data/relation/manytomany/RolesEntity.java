package com.hamitmizrak.data.relation.manytomany;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "roles")
// N(Roles) -N(Admin)
public class RolesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roles_id")
	private Long rolesId;
	
	@Column(name = "roles_name")
	private String rolesName;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	// Relation
	@ManyToMany(mappedBy = "rolesEntities", fetch = FetchType.LAZY)
	private Collection<AdminEntity> adminEntities;
	
	// parametresiz constructor
	public RolesEntity() {
	}
	
	// parametreli constructor
	public RolesEntity(Long rolesId, String rolesName, Date createdDate) {
		super();
		this.rolesId = rolesId;
		this.rolesName = rolesName;
		this.createdDate = createdDate;
	}
	
}
