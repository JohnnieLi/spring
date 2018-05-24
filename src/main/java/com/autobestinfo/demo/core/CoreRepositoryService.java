package com.autobestinfo.demo.core;

import java.util.List;
import java.util.Optional;

public interface CoreRepositoryService<T> {


    List<T> findAll();

    Optional<T>  findById(String _id);

    <S extends T> S create(S entity);

    boolean deleteById(String _id);

    T update(T o);

}
