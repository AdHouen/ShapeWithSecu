package com.shape.ShapeAppSpring.constant.filter;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shape.ShapeAppSpring.constant.SecurityConstant;
import com.shape.ShapeAppSpring.domain.HttpResponse;



@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint{

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
		
		HttpResponse httpResponse = new HttpResponse(
				HttpStatus.FORBIDDEN.value(),
				HttpStatus.FORBIDDEN,
				HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
				SecurityConstant.FORBIDDEN_MESSAGE
				);
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(FORBIDDEN.value());
		
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush();
		
		}
}
