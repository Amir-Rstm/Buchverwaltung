package com.Buchverwaltung.controller;

import com.Buchverwaltung.dto.UserRegistrationDto;
import com.Buchverwaltung.entity.User;
import com.Buchverwaltung.mapper.UserMapper;
import com.Buchverwaltung.repository.UserRepository;
import com.Buchverwaltung.security.JwtUtil;
import com.Buchverwaltung.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)

@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private UserMapper userMapper;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void registerUser_ValidDTO_ReturnsSuccessMessage() throws Exception {

        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("amir_test");
        dto.setPassword("123456");
        dto.setEmail("amir@test.com");

        when(userRepository.findByUsername("amir_test")).thenReturn(Optional.empty());

        when(userMapper.toEntity(any(UserRegistrationDto.class))).thenReturn(new User());

        when(passwordEncoder.encode("123456")).thenReturn("hashed_password");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))

                .andExpect(status().isOk())
                .andExpect(content().string("The User got saved successfully"));

    }

}
