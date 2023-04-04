package com.shape.ShapeAppSpring.constant.filter;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shape.ShapeAppSpring.constant.SecurityConstant;
import com.shape.ShapeAppSpring.domain.HttpResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
			HttpResponse httpResponse = new HttpResponse(
				UNAUTHORIZED.value(),
				UNAUTHORIZED,
				UNAUTHORIZED.getReasonPhrase().toUpperCase(),
				SecurityConstant.ACCESS_DENIED_MESSAGE		
				);
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(UNAUTHORIZED.value());
		
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush();
	}

	

}

