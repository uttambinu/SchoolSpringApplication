package com.school.schoolDemo.controller;

import com.school.schoolDemo.payload.UserDTO;
import com.school.schoolDemo.service.CustomUserDetailsService;
import com.school.schoolDemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generateToken")
    public ResponseEntity<?> generateToken(@RequestBody UserDTO userDTO) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        }catch (UsernameNotFoundException e){
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userDTO.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);

    }
}
