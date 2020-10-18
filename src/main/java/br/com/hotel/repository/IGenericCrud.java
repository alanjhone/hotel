/**
 * 
 */
package br.com.hotel.repository;

import java.util.List;
import java.util.Optional;

/**
 * @author anonymous
 *
 */
public interface IGenericCrud<T> {
	
	List<T> findAll();
    
	Optional<T> findById(long id);
    
	T save(T obj);
    
	void delete(long id);
}
