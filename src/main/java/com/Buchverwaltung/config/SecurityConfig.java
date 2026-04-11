package com.Buchverwaltung.config;

import com.Buchverwaltung.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // به اسپرینگ میگه این کلاس برای تنظیمات پایه‌ای برنامه است
@EnableWebSecurity // امنیت وب رو فعال و قابل شخصی‌سازی می‌کنه
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter; // فیلتر نگهبان ما

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ۱. آزاد کردن درگاه‌های ورود و ثبت‌نام
                        .requestMatchers("/api/auth/**").permitAll()

                        // ۲. لیست سفیدِ کامل و ضدگلوله برای سواگر
                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ۳. این خط حتماً باید آخرین دستور در این بخش باشد!
                        .anyRequest().authenticated()
                )
        // ... (اگر تنظیماتِ فیلتر JWT داری اینجا بماند)
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // این متد ابزار BCrypt رو می‌سازه و در اختیار کل برنامه قرار می‌ده
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // این متد، مدیر احراز هویت رو از دل تنظیمات اسپرینگ می‌کشه بیرون و به ما تحویل می‌ده
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }
}