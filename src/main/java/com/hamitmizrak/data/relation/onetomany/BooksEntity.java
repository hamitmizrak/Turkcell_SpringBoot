package com.hamitmizrak.data.relation.onetomany;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// lombok
@Getter
@Setter

// Entity
@Entity
@Table(name = "book")

// Book(N) Writer(1)
public class BooksEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", updatable = false)
	private long bookId;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "book_price")
	private String bookPrice;
	
	// Relation
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "writer_id", nullable = false)
	private WriterEntity writerEntity;
	
	// parametresiz constructor
	public BooksEntity() {
	}
	
	// Parametreli constructor
	public BooksEntity(String bookName, String bookPrice) {
		this.bookName = bookName;
		this.bookPrice = bookPrice;
	}
	
	public BooksEntity(long bookId, String bookName, String bookPrice) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
	}
	
}
