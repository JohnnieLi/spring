package com.autobestinfo.demo.core;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public class CoreRepositoryServiceImpl<Rep extends MongoRepository<T, ID>, T, ID> implements CoreRepositoryService<T, ID> {


    protected Rep repository;

    public CoreRepositoryServiceImpl(Rep repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }


    @Override
    public Optional<T> findById(ID id) {
        return this.repository.findById(id);
    }


    @Override
    public boolean existsById(ID id) {
        return this.repository.existsById(id);
    }


    @Override
    public <S extends T> S create(S entity) {
        return this.repository.insert(entity);
    }


    // save operation has save-or-update semantics: if an id is present, it performs an update, if not – it does an insert.
    @Override
    public <S extends T> S  update(S entity) {
        return this.repository.save(entity);
    }

    @Override
    public long count() {
        return this.repository.count();
    }


    @Override
    public boolean deleteById(ID id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean deleteAll() {
        try {
            this.repository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
