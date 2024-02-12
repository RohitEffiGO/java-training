package com.learn.app.filter;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
import jakarta.validation.constraints.NotNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UserService userService;

	public JwtAuthenticationFilter(JwtService jwtService, @Lazy UserService userService) {
		super();
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
			@NotNull FilterChain filterChain) throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		/**
		 * if no header is provided basically login or register then do this or to
		 * access basic elements no token is necessary.
		 */

		String userUrl = request.getRequestURL().toString();
		String[] urls = { "http://localhost:8080/api/auth/register", "http://localhost:8080/api/auth/login",
				"http://localhost:8080/api/category/all" };

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			for (String url : urls) {
				if (url.equals(userUrl)) {
					filterChain.doFilter(request, response);
					return;
				}
			}
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + "No token provided" + "\"}");
			return;
		}

		String token = authHeader.substring(7);
		String username = null;
		try {
			username = jwtService.extractUsername(token);
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
		}

//		If everything is valid create a session and let user do its thing.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(username);
			if (jwtService.isValid(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json");
				response.getWriter().write("{\"error\": \"" + "Token expired" + "\"}");
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
