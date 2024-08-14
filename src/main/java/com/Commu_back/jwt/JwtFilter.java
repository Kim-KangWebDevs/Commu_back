package com.Commu_back.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;
    
	//Request Header에서 토큰 정보를 꺼내오는 메소드.
	private String resolveToken(HttpServletRequest request){
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		 if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
	           return bearerToken.substring(7);
	        }
        return null;
	}

	//필터링을 실행하는 메소드
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//resolveToken을 통해 토큰 정보를 꺼내온 다음, validateToken으로 토큰이 유효한지 검사
		String jwt = resolveToken(request);
		//만약 유효하다면 Authentication을 가져와서 SecurityContext에 저장
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        //SecurityContext에서 허가된 uri 이외의 모든 Request 요청은 전부 이 필터를 거치게 되며, 
        //토큰 정보가 없거나 유효치않으면 정상적으로 수행되지 않는다.
        }
        //반대로 Request가 정상적으로 Controller까지 도착했으면 SecurityContext에 Member ID가 존재한다는 것이 보장
        filterChain.doFilter(request, response);
	}
		

}
