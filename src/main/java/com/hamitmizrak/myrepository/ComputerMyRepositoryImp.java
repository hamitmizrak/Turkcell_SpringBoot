package com.hamitmizrak.myrepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

// jpql : karmaşık sorgularımızı buna göre yazıyoruz
// delived Query: Kolay sorgularda kullanmalısın Like ,between, distinct ,
// startsWith , endsWith

@Repository // DAO
public class ComputerMyRepositoryImp implements IMyRepositoryComputer {
	
	// entityManager
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ComputerEntity> findComputerPriceMin(double computerPrice) {
		// Dikkat: buradaki her şey Entitiy classındaki değişkenlere göre yazılır.
		String jpql = "select c from ComputerEntity c where c.computerPrice>=:key";
		TypedQuery<ComputerEntity> typedQuery = entityManager.createQuery(jpql, ComputerEntity.class);
		typedQuery.setParameter("key", computerPrice);
		return typedQuery.getResultList();
	}
	
}
