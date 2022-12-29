package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDao<E>{
	
	private static EntityManagerFactory entityManagerFactory;
	
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
	}

    public JpaDao() {
        
    }

    public E create(E entity){
    	
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(entity);


        entityManager.getTransaction().commit();
        
        entityManager.close();
        return entity;
    }
    
    public E update(E entity) {
    	
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	entity = entityManager.merge(entity);
    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
		return entity;
	}
    
    public E find(Class<E> type, Object id) {

    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	E entity = entityManager.find(type, id);
    	
    	if(entity != null) {
    		entityManager.refresh(entity);
    	}
    	entityManager.getTransaction().commit();
		
    	entityManager.close();
    	
		return entity;
	}
    
    public void delete(Class<E> type, Object id) {

    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	Object reference = entityManager.getReference(type, id);
    	entityManager.getTransaction().begin();
    	entityManager.remove(reference);
    	entityManager.getTransaction().commit();
		
    	entityManager.close();
    	
	}
    
    public List<E> findWithNamedQuery(String queryName) {

    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	Query query = entityManager.createNamedQuery(queryName);
    	entityManager.getTransaction().commit();
    	
    	@SuppressWarnings("unchecked")
		List<E> results = query.getResultList();
    	
    	entityManager.close();
    	
    	return results;
	}
    
    public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue) {

    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	Query query = entityManager.createNamedQuery(queryName);
    	query.setParameter(paramName, paramValue);
    	entityManager.getTransaction().commit();
    	
    	@SuppressWarnings("unchecked")
		List<E> results = query.getResultList();
    	
    	entityManager.close();
    	
    	return results;
	}
    
    public List<E> findWithNamedQuery(String queryName, Map<String, Object> params) {

    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	Query query = entityManager.createNamedQuery(queryName);
    	
    	Set<Entry<String, Object>> setParams = params.entrySet();
    	
    	for (Entry<String, Object> entry : setParams) {
    		query.setParameter(entry.getKey(), entry.getValue());
		}
    	
    	
    	entityManager.getTransaction().commit();
    	
    	@SuppressWarnings("unchecked")
		List<E> results = query.getResultList();
    	
    	entityManager.close();
    	
    	return results;
	}
    
    public long countWithNamedQuery(String queryName) {

    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	Query query = entityManager.createNamedQuery(queryName);
    	    	
    	Object result = query.getSingleResult();
    	
    	entityManager.close();
    	
    	return (long) result;
    	
    }
    
    public void close() {
    	if(entityManagerFactory != null) {
    		entityManagerFactory.close();
    	}
    }
}
