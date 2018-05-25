package com.autobestinfo.demo.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface  UsersRepository extends MongoRepository<User,String> {

    List<User> findByFirstName(@Param("name")String name);

}
