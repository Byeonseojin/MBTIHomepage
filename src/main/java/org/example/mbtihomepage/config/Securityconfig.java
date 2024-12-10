package org.example.mbtihomepage.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Log4j2
public class Securityconfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                "/homepage/**",
                                "/homepage/login",
                                "/homepage/main",
                                "/homepage/mbti01",
                                "/homepage/mbti02",
                                "/homepage/mbti03",
                                "/homepage/mbti04",
                                "/homepage/mbti04_accessory",
                                "/mbti01_accessory/**",
                                "/register",
                                "/assets/**",
                                "/css/**",
                                "/js/**",
                                "/notice/list",
                                "/notice/read/**",
                                "/community/**"
                        ).permitAll()
                        .requestMatchers("/notice/modify/**", "/notice/**").hasRole("ADMIN")
                        .requestMatchers("/community/**", "/replies/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/homepage/login")
                        .loginProcessingUrl("/login-process")
                        .defaultSuccessUrl("/homepage/main",true)
                        .failureUrl("/homepage/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/homepage/main")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}