package dev.pizzeria.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaConnector {

	private EntityManagerFactory entityMangerFactory;

	public JpaConnector(){
		entityMangerFactory = Persistence.createEntityManagerFactory("jpa_pizza");
	}

	public boolean entryExist(Class<?> table, String condition){
		EntityManager em = entityMangerFactory.createEntityManager();

		boolean isFound;

		Query result = em.createQuery("SELECT o FROM " + table.getSimpleName() + " o WHERE "+condition);
		isFound = result.getResultList().size() != 0;
		em.close();

		return isFound;
	}

	public void insertIntoDb(Object o){
		EntityManager em = entityMangerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try{
			et.begin();

			em.persist(o);

			et.commit();

		}catch(PersistenceException e){
			et.rollback();
			e.printStackTrace();
		}finally{
			em.close();
		}
	}

	public void terminateConnection() {
		entityMangerFactory.close();
	}

	public <T> List<T> selectFromDb(Class<T> table, String conditions) {
		EntityManager em = entityMangerFactory.createEntityManager();

		TypedQuery<T> result = em.createQuery("SELECT o FROM " + table.getSimpleName() + " o " + ((conditions!=null)?"WHERE "+ conditions:"") , table);

		return result.getResultList();
	}

	public void alterFromDb(){

	}

	public void deleteFromDb(Class<?> type, int idToDelete){

		EntityManager em = entityMangerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		try{
			Object objectToDelete = em.find(type, idToDelete);

			em.remove(objectToDelete);

			et.commit();
		}catch(PersistenceException e){
			et.rollback();
			e.printStackTrace();
		}finally{
			em.close();
		}

	}

	public void mergeFromDb(Object objectToMerge, int idToDelete) {
		
		EntityManager em = entityMangerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		try{
			Object objectToUpdate = em.find(objectToMerge.getClass(), idToDelete);

			if (objectToUpdate != null){
				em.merge(objectToMerge);
			}

			et.commit();
		}catch(PersistenceException e){
			et.rollback();
			e.printStackTrace();
		}finally{
			em.close();
		}
		
	}

	/*public static <T> T entryExist(Class<T> classe){

		TypedQuery<T> querry
	}*/

}
