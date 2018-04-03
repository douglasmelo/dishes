package com.vanhack.dishes.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.vanhack.dishes.model.CustomerLoginEvent;
import com.vanhack.dishes.repository.CustomerLoginEventRepository;
import com.vanhack.dishes.utils.Environments;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

	public static final long EXPIRATION_TIME_IN_DAYS = 1;
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	
	@Autowired Environment env;
	@Autowired CustomerLoginEventRepository customerLoginEventRepository;

	public String getTokenAuthentication(String username, LocalDateTime validAt) {
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(Date.from(validAt.atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, env.getProperty(Environments.JWT_SECRET, String.class))
				.compact();
	}

	public Boolean isAuthenticated(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader(HEADER_STRING);
		
		if(Objects.nonNull(token)) {
			String email = Jwts.parser()
					.setSigningKey(env.getProperty(Environments.JWT_SECRET, String.class))
					.parseClaimsJws(token.replace(TOKEN_PREFIX, StringUtils.EMPTY))
					.getBody()
					.getSubject();
			if(Objects.nonNull(email)) {
				CustomerLoginEvent customerLoginEvent = customerLoginEventRepository.findByCustomerEmailAndLoggedTrue(email);
				if(isSameToken(token, customerLoginEvent.getToken()) && isTokenValid(customerLoginEvent.getValidAt())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isTokenValid(LocalDateTime validAt) {
		return validAt.isAfter(LocalDateTime.now());
	}

	private boolean isSameToken(String requestToken, String customerToken) {
		return requestToken.equals(customerToken);
	}

}
