package org.witchcraft.model.support.dao;

import java.util.List;

public interface GenericDAO<T> {

    T load(Long id);

    List<T> loadAll();

    List<T> searchByExample(T exampleInstance);
    
    T save(T entity);
    
    public void delete(T entity);

}
