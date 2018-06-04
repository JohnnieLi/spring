package com.autobestinfo.dev.membership;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "memberships", path = "memberships")
public interface MembershipRepository extends MongoRepository<Membership,String> {

   @Query("{ 'username' : ?0 }")
    Membership findByUsername(@Param("username")String username);


}