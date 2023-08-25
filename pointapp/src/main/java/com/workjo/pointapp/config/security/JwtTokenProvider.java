package com.workjo.pointapp.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

	@Value("${jwt.secret-key}")
	private String secretKey;
	@Value("${jwt.expiration-time}")
	private Long EXPIRATION_TIME;


	public String getUUIDString(String token) {
		return extractClaim(token, Claims::getSubject);
	}


	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}


	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, userDetails);
	}


	public String generateToken(
		Map<String, Object> extractClaims,
		UserDetails userDetails
	) {
		log.info("generateToken {} {}", extractClaims, userDetails);
		return Jwts.builder()
			.setClaims(extractClaims)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new java.util.Date(System.currentTimeMillis()))
			.setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(getSigningKey(), SignatureAlgorithm.HS256)
			.compact();
	}


	public boolean validateToken(String token, UserDetails userDetails) {
		final String loginId = getUUIDString(token);
		return (loginId.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}


	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new java.util.Date());
	}


	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}


	private Claims extractAllClaims(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}


	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}