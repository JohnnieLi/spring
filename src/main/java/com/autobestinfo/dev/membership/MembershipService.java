package com.autobestinfo.dev.membership;

import com.autobestinfo.dev.user.User;


//http://www.baeldung.com/cascading-with-dbref-and-lifecycle-events-in-spring-data-mongodb

public interface MembershipService {

    Membership createMembership(User user);

    Membership updateMembership(User user);

}

