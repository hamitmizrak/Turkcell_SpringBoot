package com.hamitmizrak.data.relation.onetomany;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends CrudRepository<BooksEntity, Long> {
	
}
