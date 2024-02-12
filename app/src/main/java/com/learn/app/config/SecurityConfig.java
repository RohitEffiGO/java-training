package com.learn.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learn.app.filter.AllowAdminFilter;
import com.learn.app.filter.JwtAuthenticationFilter;
import com.learn.app.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private UserService userDetailService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AllowAdminFilter allowAdminFilter;

	public SecurityConfig(@Lazy UserService userDetailService, @Lazy JwtAuthenticationFilter jwtAuthenticationFilter,
			AllowAdminFilter allowAdminFilter) {
		super();
		this.userDetailService = userDetailService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.allowAdminFilter = allowAdminFilter;
	}

	@Bean
	@Order(1)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req.requestMatchers("api/**").permitAll().anyRequest().anonymous())
				.userDetailsService(userDetailService)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req -> req.requestMatchers("/api/course/**").hasRole("ADMIN").anyRequest().authenticated())
				.userDetailsService(userDetailService)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterAfter(allowAdminFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
