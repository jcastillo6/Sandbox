package com.jcastillo.exchanger.model;

import java.util.List;
import java.util.Optional;

/**
 * Abstraction for the repository classes
 * @author Jorge Castillo
 *
 * @param <T>
 */
public interface DAO <T> {
	
    Optional<T> get(long id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);

}
