package com.learn.app.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AnonymousAuthFilter {

	public boolean filterUrlForLoginAndRegister(String url) {
		try {
			url = "api/" + url.split("/api/")[1];
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		List<String> allowedUrls = new ArrayList<>();
		allowedUrls.add("api/auth/register");
		allowedUrls.add("api/auth/login");

		return allowedUrls.contains(url);
	}

	public boolean filterUrlForFreeAccess(String url) {
		try {
			url = "api/" + url.split("/api/")[1];
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		List<String> allowedUrls = new ArrayList<>();
		allowedUrls.add("api/category/all");
		allowedUrls.add("api/course/fetch");

		return allowedUrls.contains(url);

	}

	public boolean authenticate(HttpServletRequest request) {
		return filterUrlForLoginAndRegister(request.getRequestURL().toString());
	}

	public boolean freeAccess(HttpServletRequest request) {
		return filterUrlForFreeAccess(request.getRequestURL().toString());
	}

}
