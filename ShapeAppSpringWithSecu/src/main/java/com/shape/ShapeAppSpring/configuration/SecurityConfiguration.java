package com.shape.ShapeAppSpring.configuration;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.shape.ShapeAppSpring.constant.filter.JwtAccessDeniedHandler;
import com.shape.ShapeAppSpring.constant.filter.JwtAuthenticationEntryPoint;
import com.shape.ShapeAppSpring.constant.filter.JwtAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@Component
@RequiredArgsConstructor
public class SecurityConfiguration extends AbstractUserDetailsAuthenticationProvider{
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public SecurityConfiguration(JwtAuthorizationFilter jwtAuthorizationFilter,
			JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	/*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests().requestMatchers(SecurityConstant.PUBLICS_URLS).permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
		.authenticationEntryPoint(JwtAuthenticationEntryPoint)
		.and()
		.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}*/
	
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http)throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		//.requestMatchers(POST, "/user/**")
		.requestMatchers("/**")
	    .permitAll()
	    .requestMatchers("/**")
	    .authenticated()
	    .anyRequest()
	    .hasAnyRole("USER", "ADMIN")
		.and()
		/*.authenticationProvider(authenticationProvider)*/
	    .httpBasic(withDefaults())
	    .sessionManagement()
	    .sessionCreationPolicy(STATELESS)
	    .and()
	    .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
	    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
	    .and()
	    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);	
		

		return http.build();
	}
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(authentication.getCredentials() == null || userDetails.getPassword() == null) {
	        throw new BadCredentialsException("Credentials may not be null");
	    }
	    if(!bCryptPasswordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword())) {
	        throw new BadCredentialsException("Invalid credentials");
	    }
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		return userDetailsService.loadUserByUsername(username);
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
	
	/* Si Adapter (qui est déprécié)
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();}
	*/
	
	
	
}