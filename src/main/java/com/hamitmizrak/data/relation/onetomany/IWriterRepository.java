package com.hamitmizrak.data.relation.onetomany;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWriterRepository extends CrudRepository<WriterEntity, Long> {
	
}
