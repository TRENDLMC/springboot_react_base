package com.packt.Cardatabase.service;

import com.packt.Cardatabase.domain.User;
import com.packt.Cardatabase.domain.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoty repositoty;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=repositoty.findByUsername(username);
        UserBuilder builder=null;
        if(user.isPresent()){
            User currentUser=user.get();
            builder=org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());

        }else{
            throw new UsernameNotFoundException("user not found");
        }
        return builder.build();
    }
}
