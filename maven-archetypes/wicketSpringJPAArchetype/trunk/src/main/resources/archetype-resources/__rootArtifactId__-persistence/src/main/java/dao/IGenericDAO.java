package ${package}.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO/Repository, providing basic CRUD (Create, Read, Update, Delete) operations.
 * 
 * @author Kamalpreet Singh
 *
 * @param <T> the entity type
 * @param <ID> the primary key type
 */
public interface IGenericDAO<T, ID extends Serializable> {

	/**
	 * Creates an entity in the persistent store.
	 * 
	 * @param entity the entity to create
	 * @return the created entity
	 */
	T create(T entity);
	
	/**
	 * Updates an entity in the persistent store.
	 * 
	 * @param entity the entity to update
	 * @return the updated entity
	 */
	T update(T entity);
	
	/**
	 * Deletes an entity from the persistent store.
	 * 
	 * @param entity the entity to delete
	 */
	void delete(T entity);
	
	/**
	 * Finds an entity by its primary key from the persistent store.
	 * 
	 * @param id the primary key
	 * @return the entity
	 */
	T findById(ID id);
	
	/**
	 * Finds all entities from the persistent store.
	 * 
	 * @return the list of entities
	 */
	List<T> findAll();
	
	/**
	 * @param queryString
	 * @param parameters
	 * @return
	 */
	List<T> executeQuery(String queryString, Object... parameters);
	
	/**
	 * @param queryString
	 * @param parameters
	 * @return
	 */
	T executeSingleResultQuery(String queryString, Object... parameters);
	
	/**
	 * Find entities based on an example.
	 * 
	 * @param exampleInstance the example
	 * @return the list of entities
	 */
	//List<T> findByExample(T exampleInstance);
	
	// TODO: java docs
	//List<T> findByExample(T exampleInstance, String... excludeProperty);
}
