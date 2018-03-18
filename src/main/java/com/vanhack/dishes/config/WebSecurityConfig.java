package com.vanhack.dishes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Configuration
	@Order(1)
	public static class ApiSecurityWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			.antMatchers("api/v1/customer/**")
			.permitAll()
			.and()
				.csrf().disable();
			
//			http
//				.antMatcher("/api/v1/customer/**")
//				.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
//				.headers().and()
//				.sessionManagement().sessionFixation().migrateSession().and()
//				.securityContext()
//				.and()
//					.httpBasic()
//				.and()
//					.csrf().disable();
		}
	}
}
