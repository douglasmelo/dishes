package com.vanhack.dishes.authentication.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import com.vanhack.dishes.security.TokenAuthenticationService;

@Service
public class SessionExpiredFilter extends GenericFilterBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionExpiredFilter.class);
	
	private static final String REQUEST_STYLESHEETS = "stylesheets";
	private static final String REQUEST_JAVASCRIPT = "javascript";
	private static final String REQUEST_IMAGES = "images";
	private static final String REQUEST_FAVICON = "favicon";
	private static final String REQUEST_MONITOR = "monitor";
	private static final String REQUEST_SWAGGER = "swagger";
	private static final String REQUEST_API_SECURITY_AUTH = "/api/v1/customer";
	private static final String REQUEST_SWAGGER_API_DOCS = "api-docs";
	
	@Autowired TokenAuthenticationService tokenAuthenticationService;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		
		if (httpServletRequest.getRequestURI().contains(REQUEST_MONITOR)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if (isUrlToBeValidated(httpServletRequest)) {
			String uri = fetchUri(httpServletRequest);
			if(!tokenAuthenticationService.isAuthenticated(httpServletRequest)) {
				LOGGER.info("Unauthorized Method, do not have any permission to access this uri {}", uri);
				httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Unauthorized Method, do not have any permission to access this uri " + uri);
				return;
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	private boolean isUrlToBeValidated(HttpServletRequest httpServletRequest) {
		return !httpServletRequest.getRequestURI().contentEquals("/")
				&& !httpServletRequest.getRequestURI().contains(REQUEST_STYLESHEETS)
				&& !httpServletRequest.getRequestURI().contains(REQUEST_JAVASCRIPT)
				&& !httpServletRequest.getRequestURI().contains(REQUEST_IMAGES)
				&& !httpServletRequest.getRequestURI().contains(REQUEST_SWAGGER)
				&& !httpServletRequest.getRequestURI().contains(REQUEST_SWAGGER_API_DOCS)
				&& !httpServletRequest.getRequestURI().contains(REQUEST_MONITOR)
				&& !httpServletRequest.getRequestURI().contains(REQUEST_API_SECURITY_AUTH)
				&& !httpServletRequest.getRequestURI().contains(REQUEST_FAVICON);
	}
	
	private String fetchUri(HttpServletRequest httpServletRequest) {
		String uri = httpServletRequest.getHeader("Uri");
		return Objects.nonNull(uri) ? uri : httpServletRequest.getRequestURI();
	}

}
