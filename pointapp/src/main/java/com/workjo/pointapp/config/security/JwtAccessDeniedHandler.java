package com.workjo.pointapp.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	private final ObjectMapper objectMapper;


	@Override
	public void handle(HttpServletRequest request,
		HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {
		log.info("jwt - jwt AccessDeniedHandler ");

		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setStatus(ErrorCode.UNAUTHORIZED.getStatus().value());

		String result = objectMapper.writeValueAsString(ApiResponse.ofError(ErrorCode.UNAUTHORIZED));
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

}