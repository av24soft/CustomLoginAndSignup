package com.avsoft.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(
						authz -> authz.requestMatchers("/signup", "/login").permitAll().anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/home", true)
						.failureUrl("/login?error=true").permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/login?logout=true").permitAll()).build();

	}
	
	@Bean
     BCryptPasswordEncoder BCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    } 
	
}
