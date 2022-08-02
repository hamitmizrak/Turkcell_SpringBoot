package com.hamitmizrak.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hamitmizrak.data.entity.ProductEntity;

@Repository
// CrudRepository
// JpaRepository
public interface IProductRepository extends CrudRepository<ProductEntity, Long> {
	
	// DeliveredQuery
	// kendi sorgularımızı yazalım. ==> DeliveredQuery
	
	// Dikkat: Biz sql tabloları üzerinde ilerlemiyoruz biz @Entity classları
	// üzerinde ilerliyoruz.
	// Dikkat:camelCase yazarak devam ediyoruz.
	// Entity attributes => ProductEntity, productName,productCode
	
	// 1.ADIM ==> karar vermek ==> find veya delete veya count veya get
	// List<ProductEntity> find
	
	// 2.ADIM ==> Entity adını yazıyoruz
	// List<ProductEntity> findProductEntities
	
	// 3.ADIM ==> sonra By(ile) bağlaç yazıyoruz.
	// List<ProductEntity> findProductEntitiesBy
	
	// 4.ADIM ==> Entity variable yazıyoruz
	// List<ProductEntity> findProductEntitiesByProductName
	
	// 5.ADIM ==> parametresini yazmak ==> productName
	List<ProductEntity> findProductEntitiesByProductName(String productName);
	
	List<ProductEntity> findByProductName(String productName);
	
	List<ProductEntity> findByProductCode(String productCode);
	
	// startingWith: ile başlayan
	List<ProductEntity> findByProductNameStartingWith(String productName);
	
	List<ProductEntity> findByProductNameStartsWith(String productName);
	
	// endsWith: ile biten
	List<ProductEntity> findByProductNameEndsWith(String productName);
	
	// equals
	List<ProductEntity> findByProductNameEquals(String productName);
	
	// like
	List<ProductEntity> findByProductNameLike(String productName);
	
	// price
	List<ProductEntity> findByProductPrice(double productPrice);
	
	// GreaterThan: verilen sayıadan büyük olanları
	List<ProductEntity> findByProductPriceGreaterThan(double productPrice);
	
	// between
	List<ProductEntity> findByProductPriceBetween(double productPriceMin, double productPriceMax);
	
}
