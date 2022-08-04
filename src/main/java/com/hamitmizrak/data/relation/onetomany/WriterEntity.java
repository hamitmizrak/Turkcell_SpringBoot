package com.hamitmizrak.data.relation.onetomany;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// lombok
@Getter
@Setter

// Entity
@Entity
@Table(name = "writer")

// Writer(1) Book(N)
public class WriterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Object variable
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "writer_id", updatable = false)
	private Long writerId;
	
	@Column(name = "writer_name")
	private String writerName;
	
	@Column(name = "writer_surname")
	private String writerSurname;
	
	// Relation
	@OneToMany(mappedBy = "writerEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<BooksEntity> bookListEntities;
	
	// parametresiz constructor
	public WriterEntity() {
		
	}
	
	// Parametreli constructor
	public WriterEntity(long writerId, String writerName, String writerSurname) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.writerSurname = writerSurname;
	}
	
	public WriterEntity(String writerName, String writerSurname) {
		this.writerName = writerName;
		this.writerSurname = writerSurname;
	}
	
}
