package com.learn.app.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AnonymousAuthFilter {

	public boolean filterUrlForLoginAndRegister(String url) {
		List<String> allowedUrls = new ArrayList<>();
		allowedUrls.add("http://localhost:8080/api/auth/register");
		allowedUrls.add("http://localhost:8080/api/auth/login");

		return allowedUrls.contains(url);
	}

	public boolean filterUrlForFreeAccess(String url) {
		List<String> allowedUrls = new ArrayList<>();
		allowedUrls.add("http://localhost:8080/api/category/all");

		boolean result = allowedUrls.contains(url);

		if (url.startsWith("http://localhost:8080/api/course/fetch"))
			result = true;

		return result;
	}

	public boolean authenticate(HttpServletRequest request) {
		return filterUrlForLoginAndRegister(request.getRequestURL().toString());
	}

	public boolean freeAccess(HttpServletRequest request) {
		return filterUrlForFreeAccess(request.getRequestURL().toString());
	}

}
