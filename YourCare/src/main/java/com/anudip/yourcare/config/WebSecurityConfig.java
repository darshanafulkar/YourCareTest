package com.anudip.yourcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.anudip.yourcare.service.impl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{
	
	@Bean
	public UserDetailsService userDetailService()
	{
		return new MyUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChai(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/user/create","/review/**","/query/**","appointment/create")
		.permitAll().and().authorizeHttpRequests()

		.requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/admin/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/admin/**").hasAnyAuthority("ADMIN")
		
		
		.requestMatchers(HttpMethod.POST, "/doctors/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/doctors/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/doctors/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/doctors/**").hasAnyAuthority("ADMIN")
	
		.requestMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ADMIN","USER")
		.requestMatchers(HttpMethod.PUT, "/user/**").hasAnyAuthority("ADMIN","USER")
		.requestMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ADMIN")
		
		.requestMatchers(HttpMethod.GET, "/appointments/**").hasAnyAuthority("ADMIN")
		
		
		.anyRequest()
		.authenticated().and().formLogin().permitAll().and().httpBasic();
		
		
		return http.build();
	}
	
}