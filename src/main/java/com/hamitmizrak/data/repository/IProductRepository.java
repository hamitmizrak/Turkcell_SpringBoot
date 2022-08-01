package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.ProductEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Long> {
	
}
