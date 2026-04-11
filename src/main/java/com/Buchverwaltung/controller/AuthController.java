package com.Buchverwaltung.controller;

import com.Buchverwaltung.dto.UserRegistrationDto;
import com.Buchverwaltung.entity.User;
import com.Buchverwaltung.mapper.UserMapper;
import com.Buchverwaltung.repository.UserRepository;
import com.Buchverwaltung.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;


    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {

        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            return "This username exists.";
        }

        User newUser = userMapper.toEntity(registrationDto);

        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setRole("USER");

        userRepository.save(newUser);

        return "The User got saved successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        return jwtUtil.generateToken(user.getUsername());
    }
}

