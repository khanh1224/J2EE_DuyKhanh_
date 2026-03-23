package com.example.bai4.config;

import com.example.bai4.service.AccountService;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AccountService accountService) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    // 🔐 ADMIN
                    .requestMatchers("/products/create").hasRole("ADMIN")
                    .requestMatchers("/products/edit/**").hasRole("ADMIN")
                    .requestMatchers("/products/delete/**").hasRole("ADMIN")

                    // 👤 USER + ADMIN
                    .requestMatchers("/products/**").hasAnyRole("USER", "ADMIN")

                    // 🌐 còn lại
                    .anyRequest().permitAll()
            )

            // 🔥 dùng DB login
            .userDetailsService(accountService)

            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/products", true) // 🔥 sửa ở đây
                    .permitAll()
            )

            .logout(logout -> logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            );

        return http.build();
    }
}