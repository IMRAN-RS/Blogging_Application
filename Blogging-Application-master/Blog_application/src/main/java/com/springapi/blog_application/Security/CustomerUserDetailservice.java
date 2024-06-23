package com.springapi.blog_application.Security;

import com.springapi.blog_application.Exception.ResourceNotFoundException;
import com.springapi.blog_application.Model.User;
import com.springapi.blog_application.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailservice implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // loading user from database
        User user = userRepo.findByEmail(username).orElseThrow( ()->new ResourceNotFoundException("User", "Email"+username, 0));

        return user;
    }
}
