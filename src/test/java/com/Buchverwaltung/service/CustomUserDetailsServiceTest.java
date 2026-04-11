package com.Buchverwaltung.service;

import com.Buchverwaltung.entity.User;
import com.Buchverwaltung.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void loadByUsername_UserExists_ReturnsUserDetails() {

        User mockUser = new User();
        mockUser.setUsername("ali");
        mockUser.setPassword("hashedpassword123");
        mockUser.setRole("ADMIN");

        when(userRepository.findByUsername("ali")).thenReturn(Optional.of(mockUser));

        UserDetails result = customUserDetailsService.loadUserByUsername("ali");

        assertNotNull(result);
        assertEquals("ali", result.getUsername());
        assertTrue(result.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));

        verify(userRepository, times(1)).findByUsername("ali");
    }

}
