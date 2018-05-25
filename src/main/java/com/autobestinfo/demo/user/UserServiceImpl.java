package com.autobestinfo.demo.user;

import com.autobestinfo.demo.core.CoreRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends CoreRepositoryServiceImpl<UsersRepository, User, String> implements UserService {



    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        super(usersRepository);
    }



}
