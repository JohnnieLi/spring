package com.autobestinfo.dev.core;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;


public class CoreRepositoryServiceImpl<Rep extends MongoRepository<T, ID>, T, ID> implements CoreRepositoryService<T, ID> {


    protected Rep repository;

    public CoreRepositoryServiceImpl(Rep repository) {
        this.repository = repository;
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }


    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Override
    public Optional<T> findById(ID id) {
        return this.repository.findById(id);
    }


    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Override
    public boolean existsById(ID id) {
        return this.repository.existsById(id);
    }


    /**
     * Inserts the given entity. Assumes the instance to be new to be able to apply insertion optimizations. Use the
     * returned instance for further operations as the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity
     * @since 1.7
     */
    @Override
    public <S extends T> S create(S entity) {
        return this.repository.save(entity);
    }


    /**
     * update the given entity. Assumes the instance to be new to be able to apply insertion optimizations. Use the
     * returned instance for further operations as the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved(updated) entity
     * @since 1.7
     */
    // save operation has save-or-update semantics: if an id is present, it performs an update, if not – it does an insert.
    @Override
    public <S extends T> S  update(S entity) {
        return this.repository.save(entity);
    }


    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    @Override
    public long count() {
        return this.repository.count();
    }


    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @Override
    public boolean deleteById(ID id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Deletes all entities managed by the repository.
     */
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
