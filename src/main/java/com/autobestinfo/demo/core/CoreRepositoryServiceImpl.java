package com.autobestinfo.demo.core;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;


public class CoreRepositoryServiceImpl<Rep extends MongoRepository<T,String>, T> implements CoreRepositoryService<T> {


    protected Rep repository;


    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<T> findById(String _id) {
        return this.repository.findById(_id);
    }

    @Override
    public <S extends T> S create(S entity) {
        return null;
    }

    @Override
    public boolean deleteById(String _id) {
        return false;
    }

    @Override
    public T update(T o) {
        return null;
    }
}
