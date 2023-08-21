package com.workjo.pointapp.config.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTHORITIES_HEADER_KEY = "Authorization";

	private final JwtTokenProvider jwtTokenProvider;
	private final UserDetailsService userDetailsService;


	@Override
	protected void doFilterInternal(
		@NonNull
		HttpServletRequest request,
		@NonNull
		HttpServletResponse response,
		@NonNull
		FilterChain filterChain
	) throws ServletException, IOException {
		final String authHeader = request.getHeader(AUTHORITIES_HEADER_KEY);
		final String jwt;
		final String loginId;
		log.info("userId - {}", authHeader);
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			//			response.sendError(403);
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		loginId = jwtTokenProvider.getUUID(jwt);
		log.info("userId - {}", loginId);
		if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
			log.info("userDetails - {} {}", userDetails, userDetails.getUsername());
			if (jwtTokenProvider.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.getAuthorities()
				);
				authenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request)
				);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			filterChain.doFilter(request, response);
		}
	}

}