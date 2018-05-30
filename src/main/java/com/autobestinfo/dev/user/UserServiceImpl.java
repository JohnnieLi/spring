package com.autobestinfo.dev.user;

import com.autobestinfo.dev.core.CoreRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends CoreRepositoryServiceImpl<UsersRepository, User, String> implements UserService{


    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        super(usersRepository);
    }


    public User addTest() {
        User user = new User();
        user.setUsername("tester");
        user.setPassword("testpass");
        return this.repository.save(user);
    }


    @Override
    public void login(User user) {

    }

    @Override
    public void updateUser(User user) {

    }



}
