package com.school.schoolDemo.service;

import com.school.schoolDemo.entity.User;
import com.school.schoolDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(userRepository.findByUsername(username).isPresent()){
            User user = userRepository.findByUsername(username).get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User with username " + username + "does not exist!");
        }
    }
}
