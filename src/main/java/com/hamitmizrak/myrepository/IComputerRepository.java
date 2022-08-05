package com.hamitmizrak.myrepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComputerRepository extends CrudRepository<ComputerEntity, Long> {
	
}
