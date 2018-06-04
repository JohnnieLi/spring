package com.autobestinfo.dev.core.security;
import com.autobestinfo.dev.user.User;
import com.autobestinfo.dev.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Implement UserDetailsService and provide loadUser functions for AuthenticationFilters
 *
 * @author Jiangqi Li
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    /**
     * Get User object from DAO
     * <p>
     * This might be used for AuthenticationFilter to set User Principal object
     * </p>
     * @param username String
     * @return User
     * @throws UsernameNotFoundException
     */
     static User findUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = usersRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = usersRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USERS");
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
    }

}
