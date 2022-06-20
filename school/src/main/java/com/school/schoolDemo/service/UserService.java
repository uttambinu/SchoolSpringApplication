package com.school.schoolDemo.service;

import com.school.schoolDemo.entity.User;
import com.school.schoolDemo.payload.UserDTO;
import com.school.schoolDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private BCryptPasswordEncoder passwordEncoder;

    public User readUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public void createUser(UserDTO userDTO){
        User user = new User();
        Optional<User> byUsername = Optional.ofNullable(readUserByUsername(userDTO.getUsername()));
        if(byUsername.isPresent()){
            throw new RuntimeException("User already exist. Please use different username!");
        }
        else {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
        }
    }
}
