package com.autobestinfo.demo.core;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CoreRepositoryService<T, ID> {


    List<T> findAll();


    Optional<T>  findById(ID id);


    boolean existsById(ID id);


    <S extends T> S create(S entity);


    boolean deleteById(ID id);


    boolean deleteAll();


    <S extends T> S update(S entity);


    long count();



}
