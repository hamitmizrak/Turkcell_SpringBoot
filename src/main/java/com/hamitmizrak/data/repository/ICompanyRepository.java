package com.hamitmizrak.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hamitmizrak.data.entity.CompanyEntity;

// CrudRepository
// JpaRepository
// PagingAndSortingRepository
public interface ICompanyRepository extends PagingAndSortingRepository<CompanyEntity, Long> {
	// Delived Query
	
	/*
	 * // DeliveredQuery
	 * // kendi sorgularımızı yazalım. ==> DeliveredQuery
	 * // Dikkat: Biz sql tabloları üzerinde ilerlemiyoruz biz @Entity classları
	 * // üzerinde ilerliyoruz.
	 * // Dikkat:camelCase yazarak devam ediyoruz.
	 * // Entity attributes => CompanyEntity, CompanyName,productCode
	 * // 1.ADIM ==> karar vermek ==> find veya delete veya count veya get
	 * // List<CompanyEntity> find
	 * // 2.ADIM ==> Entity adını yazıyoruz
	 * // List<CompanyEntity> findCompanyEntities
	 * // 3.ADIM ==> sonra By(ile) bağlaç yazıyoruz.
	 * // List<CompanyEntity> findCompanyEntitiesBy
	 * // 4.ADIM ==> Entity variable yazıyoruz
	 * // List<CompanyEntity> findCompanyEntitiesByCompanyName
	 * // 5.ADIM ==> parametresini yazmak ==> CompanyName
	 * List<CompanyEntity> findCompanyEntitiesByCompanyName(String CompanyName);
	 * List<CompanyEntity> findByCompanyName(String CompanyName);
	 * List<CompanyEntity> findByProductCode(String productCode);
	 * // startingWith: ile başlayan
	 * List<CompanyEntity> findByCompanyNameStartsWith(String CompanyName);
	 * List<CompanyEntity> findByCompanyNameStartingWith(String CompanyName);
	 * List<CompanyEntity> findByCompanyNameIsStartingWith(String CompanyName);
	 * // endsWith: ile biten
	 * List<CompanyEntity> findByCompanyNameEndsWith(String CompanyName);
	 * // equals veya IS kullanabiliriz
	 * List<CompanyEntity> findByCompanyNameEquals(String CompanyName);
	 * List<CompanyEntity> findByCompanyNameIs(String CompanyName);
	 * // Not veya isNot
	 * List<CompanyEntity> findByCompanyNameNot(String CompanyName);
	 * List<CompanyEntity> findByCompanyNameIsNot(String CompanyName);
	 * // distinct: tekrar etmeyen
	 * List<CompanyEntity> findDistinctByCompanyName(String CompanyName);
	 * // like
	 * List<CompanyEntity> findByCompanyNameLike(String CompanyName);
	 * // price
	 * List<CompanyEntity> findByCompanyTaxNumber(double CompanyTaxNumber);
	 * // GreaterThan: verilen sayıdan büyük olanları
	 * List<CompanyEntity> findByCompanyTaxNumberGreaterThan(double
	 * CompanyTaxNumber);
	 * // between
	 * List<CompanyEntity> findByCompanyTaxNumberBetween(double CompanyTaxNumberMin,
	 * double
	 * CompanyTaxNumberMax);
	 * // çoklu arama
	 * List<CompanyEntity> findByCompanyNameOrProductCodeAllIgnoreCase(String
	 * CompanyName, String productCode);
	 * // order by: ASC küçükten büyüğe sıralama
	 * List<CompanyEntity> findByCompanyNameContainingOrderByCompanyName(String
	 * CompanyName);
	 * List<CompanyEntity> findByCompanyNameContainingOrderByCompanyNameAsc(String
	 * CompanyName);
	 * // order by: DESC büyükten küçüğe sıralama
	 * List<CompanyEntity> findByCompanyNameContainingOrderByCompanyNameDesc(String
	 * CompanyName);
	 * // limit en üsteki 1 : Dikkat By önce yaz
	 * List<CompanyEntity> findFirstByOrderById();
	 * // limit en üsteki 1
	 * List<CompanyEntity> findTopByOrderByCompanyName();
	 * // limit en üsteki 4
	 * List<CompanyEntity> findFirst4ByOrderById();
	 * // delete
	 * void deleteById(int id);
	 * // null not null: buna dikkat et hatalar çıkartabilir
	 * // List<CompanyEntity> findByCompanyNameIsNull(String CompanyName);
	 * // List<CompanyEntity> findByCompanyNameIsNotNull(String CompanyName);
	 * // count
	 * public int countByCompanyName(String CompanyName);
	 */
	
}
