package com.hamitmizrak.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hamitmizrak.data.entity.ProductEntity;

@Repository
// CrudRepository
// JpaRepository
public interface IProductRepository extends CrudRepository<ProductEntity, Long> {
	
}
