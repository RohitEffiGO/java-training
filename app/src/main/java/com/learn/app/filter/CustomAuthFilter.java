package com.learn.app.filter;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learn.app.service.JwtService;
import com.learn.app.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UserService userService;
	private final AnonymousAuthFilter anonymousFilter;
	private final AuthorAuthFilter authorFilter;
	private final LearnerAuthFilter learnerFilter;

	public CustomAuthFilter(JwtService jwtService, UserService userService, AnonymousAuthFilter anonymousFilter,
			AuthorAuthFilter authorFilter, LearnerAuthFilter learnerFilter) {
		super();
		this.jwtService = jwtService;
		this.userService = userService;
		this.anonymousFilter = anonymousFilter;
		this.authorFilter = authorFilter;
		this.learnerFilter = learnerFilter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			/**
			 * Categorize as anonymous and send to anonymous auth.
			 */

			if (anonymousFilter.authenticate(request) || anonymousFilter.freeAccess(request)) {
				filterChain.doFilter(request, response);
				return;
			}

			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "JWT header missing" + "\"}");
			return;
		}

		String token = null;
		String username = null;
		Object[] iterateRole = null;
		try {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
			UserDetails userDetails = userService.loadUserByUsername(username);
			iterateRole = userDetails.getAuthorities().toArray();
		} catch (SignatureException exception) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "Invalid JWT token" + "\"}");
			return;
		} catch (ExpiredJwtException exception) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "Token expired" + "\"}");
			return;
		} catch (NullPointerException error) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "role type null" + "\"}");
			return;
		} catch (Exception error) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + error.toString() + "\"}");
		}

		/*
		 * Look for Admin then look for author the look for learner.
		 */

		String role = null;

		try {
			role = iterateRole[0].toString();
		} catch (NullPointerException error) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "role type null" + "\"}");
			return;
		}

		switch (role) {
		case "ADMIN": {
			filterChain.doFilter(request, response);
			break;
		}
		case "AUTHOR": {
			if (authorFilter.filterUrl(request)) {
				filterChain.doFilter(request, response);
				break;
			}

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "role type invalid" + "\"}");
			return;
		}
		case "LEARNER": {
			if (learnerFilter.filterUrl(request)) {
				filterChain.doFilter(request, response);
				break;
			}

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "role type invalid" + "\"}");
			return;
		}
		default: {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "Something is fishy about you." + "\"}");
		}
		}

	}
}
