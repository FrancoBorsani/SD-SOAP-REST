package com.example.modulocorreo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.modulocorreo.dto.request.LoginRequest;
import com.example.modulocorreo.dto.response.LoginResponse;
import com.example.modulocorreo.entities.User;
import com.example.modulocorreo.repositories.IUserRepository;
import com.example.modulocorreo.services.implementation.UserService;
import com.example.modulocorreo.utils.JwtUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);
        
        User user = userRepository.findByUsername(userDetails.getUsername());
                
        return ResponseEntity.ok(new LoginResponse(jwt, user.getUsername(), user.getNombre(), user.getApellido(), user.getEmail(), user.getDocumento()));
    }

}