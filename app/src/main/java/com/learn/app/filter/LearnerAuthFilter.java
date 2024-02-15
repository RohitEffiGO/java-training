package com.learn.app.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class LearnerAuthFilter {

	public boolean filterUrl(HttpServletRequest request) {
		String userUrl = request.getRequestURL().toString();
		List<String> urls = new ArrayList<>();

		urls.add("http://localhost:8080/api/fav");
		urls.add("http://localhost:8080/api/enroll");

		AnonymousAuthFilter anonymousFilter = new AnonymousAuthFilter();
		if (anonymousFilter.authenticate(request) || anonymousFilter.freeAccess(request)) {
			return true;
		}

		for (String url : urls) {
			if (userUrl.startsWith(url)) {
				return true;
			}
		}

		return false;
	}

}
