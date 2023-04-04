package com.shape.ShapeAppSpring.constant.filter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.shape.ShapeAppSpring.utility.JWTTokenProvider;
import static com.shape.ShapeAppSpring.constant.SecurityConstant.*;



@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{

	private JWTTokenProvider jwtTokenProvider;
	
	
	public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
		
		this.jwtTokenProvider = jwtTokenProvider;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
			response.setStatus(OK.value());
		}else {
			String authorizationHeader = request.getHeader(AUTHORIZATION);
			
			if(authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;   //We stop the execution of the method for returning nothing 
		}
		
		String token = authorizationHeader.substring(TOKEN_PREFIX.length());  //We remove the word Bearer
		String username = jwtTokenProvider.getSubject(token);
		
		if(jwtTokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication()==null) {
			List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
			Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}else {
			SecurityContextHolder.clearContext();
		}
		}
		filterChain.doFilter(request, response);
	}

}
