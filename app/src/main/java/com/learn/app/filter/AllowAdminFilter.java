package com.learn.app.filter;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learn.app.service.JwtService;
import com.learn.app.service.UserService;

import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AllowAdminFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UserService userService;

	public AllowAdminFilter(JwtService jwtService, UserService userService) {
		super();
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		String userUrl = request.getRequestURL().toString();
		String[] url = { "http://localhost:8080/api/auth/register", "http://localhost:8080/api/auth/login" };

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			if (userUrl.equals(url[0]) || userUrl.equals(url[1])) {
				filterChain.doFilter(request, response);
				return;
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
		}

		UserDetails userDetails = userService.loadUserByUsername(username);

		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

		Object[] iterateRole = roles.toArray();
		for (Object role : iterateRole) {
			if (role.toString().equals("ADMIN")) {
				filterChain.doFilter(request, response);
				return;
			}
		}

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.getWriter().write("{\"error\": \"" + "Role type invalid" + "\"}");
	}

}
