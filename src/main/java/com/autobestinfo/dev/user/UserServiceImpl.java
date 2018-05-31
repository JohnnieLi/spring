package com.autobestinfo.dev.user;

import com.autobestinfo.dev.core.CoreRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends CoreRepositoryServiceImpl<UsersRepository, User, String> implements UserService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        super(usersRepository);
        this.passwordEncoder = passwordEncoder;
    }


    public User addTest() {
        User user = new User();
        user.setUsername("tester2");
        String pass = "testpass";
        String encodedPass = this.passwordEncoder.encode(pass);
        user.setPassword(encodedPass);
        return this.repository.save(user);
    }


    @Override
    public void login(User user) {

    }

    @Override
    public User findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public void updateUser(User user) {

    }


}
