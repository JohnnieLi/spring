package com.autobestinfo.dev.membership;

import com.autobestinfo.dev.core.CoreRepositoryServiceImpl;
import com.autobestinfo.dev.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//https://docs.spring.io/spring-data/data-document/docs/current/reference/html/#mongodb.repositories.queries
@Service
public class MembershipServiceImpl  extends CoreRepositoryServiceImpl<MembershipRepository, Membership, String> implements MembershipService{


    @Autowired
    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        super(membershipRepository);
    }

    @Override
    public Membership createMembership(User user) {
        Membership membership = new Membership();
        membership.setUsername(user.getUsername());
        membership.setPeriod("Yearly");
        return this.repository.save(membership);
    }


    @Override
    public Membership updateMembership(User user) {
        Membership membership = this.repository.findByUsername(user.getUsername());
        if(membership!=null){
            membership.setPeriod("Monthly");
            return this.repository.save(membership);
        }
        return null;
    }


    public Boolean deleteMembership(User user) {
        Membership membership = this.repository.findByUsername(user.getUsername());
        return this.deleteById(membership.get_id());
    }
}
