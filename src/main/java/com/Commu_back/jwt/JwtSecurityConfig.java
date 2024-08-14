package com.Commu_back.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> 인터페이스를 구현
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	//TokenProvider와 JwtFilter를 SecurityConfig에 적용할 때 사용
	private final TokenProvider tokenProvider;
	@Override
	//configure은TokenProvider를 주입받아서 JwtFilter를 통해 SecurityConfig 안에 필터를 등록
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
