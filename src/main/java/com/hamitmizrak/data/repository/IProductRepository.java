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
	List<ProductEntity> findByProductNameStartsWith(String productName);
	
	List<ProductEntity> findByProductNameStartingWith(String productName);
	
	List<ProductEntity> findByProductNameIsStartingWith(String productName);
	
	// endsWith: ile biten
	List<ProductEntity> findByProductNameEndsWith(String productName);
	
	// equals veya IS kullanabiliriz
	List<ProductEntity> findByProductNameEquals(String productName);
	
	List<ProductEntity> findByProductNameIs(String productName);
	
	// Not veya isNot
	List<ProductEntity> findByProductNameNot(String productName);
	
	List<ProductEntity> findByProductNameIsNot(String productName);
	
	// distinct: tekrar etmeyen
	List<ProductEntity> findDistinctByProductName(String productName);
	
	// like
	List<ProductEntity> findByProductNameLike(String productName);
	
	// price
	List<ProductEntity> findByProductPrice(double productPrice);
	
	// GreaterThan: verilen sayıadan büyük olanları
	List<ProductEntity> findByProductPriceGreaterThan(double productPrice);
	
	// between
	List<ProductEntity> findByProductPriceBetween(double productPriceMin, double productPriceMax);
	
	// çoklu arama
	List<ProductEntity> findByProductNameOrProductCodeAllIgnoreCase(String productName, String productCode);
	
	// order by: ASC küçükten büyüğe sıralama
	List<ProductEntity> findByProductNameContainingOrderByProductName(String productName);
	
	List<ProductEntity> findByProductNameContainingOrderByProductNameAsc(String productName);
	
	// order by: DESC büyükten küçüğe sıralama
	List<ProductEntity> findByProductNameContainingOrderByProductNameDesc(String productName);
	
	// limit en üsteki 1
	List<ProductEntity> findFirstByOrderById();
	
	// limit en üsteki 1
	List<ProductEntity> findTopByOrderByProductName();
	
	// limit en üsteki 4
	List<ProductEntity> findFirst4ByOrderById();
	
	// delete
	void deleteById(int id);
	
	// null not null: buna dikkat et hatalar çıkartabilir
	// List<ProductEntity> findByProductNameIsNull(String productName);
	// List<ProductEntity> findByProductNameIsNotNull(String productName);
	
	// count
	public int countByProductName(String productName);
	
}
