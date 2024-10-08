package com.Commu_back.jwt;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 유효한 자격증명을 제공하지 않고 접근하려 할 때, 401 Unauthorized 에러를 리턴할 클래스

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler{
	 @Override
	 public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
	        // 필요한 권한이 없이 접근하려 할때 403
	        response.sendError(HttpServletResponse.SC_FORBIDDEN);
	    }
}
