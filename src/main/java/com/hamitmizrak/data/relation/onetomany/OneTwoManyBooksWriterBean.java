package com.hamitmizrak.data.relation.onetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Writer(1) Book (N)
public class OneTwoManyBooksWriterBean {
	
	@Autowired
	IWriterRepository writerRepository;
	
	@Autowired
	IBookRepository bookRepository;
	
	@Bean
	public void oneToManyBean() {
		
		WriterEntity writerEntity = new WriterEntity();
		writerEntity.setWriterName("Neşat Nuri");
		writerEntity.setWriterSurname("Gültekin");
		writerRepository.save(writerEntity);
		
		// -- SQL eklemede önce Tek olan (1) eklenir
		// select * from writer;
		// insert into writer (writer_name,writer_surname) values ('Neşat
		// Nuri','Gültekin');
		
		BooksEntity booksEntity = new BooksEntity();
		booksEntity.setBookName("Çalıkuşu");
		booksEntity.setBookPrice("85");
		booksEntity.setWriterEntity(writerEntity);
		bookRepository.save(booksEntity);
		
		// select * from book;
		// insert into book (book_name,book_price,writer_id) values
		// ('Çalıkuşu','55TL',1);ß
		
		// -- silmede önce Çok olan (N) silinir
		// delete from writer where writer_id=1;
		// delete from book where book_id=1;
	}
}
