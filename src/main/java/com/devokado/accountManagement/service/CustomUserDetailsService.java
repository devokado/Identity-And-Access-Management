package com.devokado.accountManagement.service;

import com.devokado.accountManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetail = userService.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
        return new org.springframework.security.core.userdetails.User(userDetail.getUsername(),
                userDetail.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
