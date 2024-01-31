package com.AuthRegLog.Login.config;

import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {
	private static final String SECRET = "your-secret-key";
	private static final long EXPIRATION_TIME = 864_000_000; // 10 days

	@SuppressWarnings("deprecation")
	public static String generateToken(String username) {
		return Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}

	@SuppressWarnings("deprecation")
	public static String extractUsername(String token) {
		return Jwts.parser().setSigningKey(SECRET)
				.parseClaimsJws(token).getBody()
				.getSubject();
	}
}
