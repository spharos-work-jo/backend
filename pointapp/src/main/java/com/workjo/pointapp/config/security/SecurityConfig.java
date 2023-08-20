package com.workjo.pointapp.config.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtTokenProvider;
	private final AuthenticationProvider authendicationProvider;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		log.debug("security filter");

		http
			.csrf(CsrfConfigurer::disable)
			.authorizeHttpRequests(
				authorizeHttpRequests -> authorizeHttpRequests
					.requestMatchers("/api/v1/user/find-for-gift")
					.authenticated()
			)
			.authorizeHttpRequests(
				authorizeHttpRequests -> authorizeHttpRequests
					.requestMatchers("/api/v1/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs/**")
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.sessionManagement(
				sessionManagement -> sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authenticationProvider(authendicationProvider)
			.addFilterBefore(jwtTokenProvider, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
