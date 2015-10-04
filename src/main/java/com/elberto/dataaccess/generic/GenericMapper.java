package com.elberto.dataaccess.generic;

import java.util.List;

public interface GenericMapper<T> 
{
    List<T> findAll();
    int insert(T record);
    T getById(Object id);
    void update(T object);
    void deleteById(Object id);

}
