package com.Commu_back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Commu_back.service.AuthService;

@Configuration
@EnableWebSecurity
public class WebSecurityCongfig{
	@Autowired
	private AuthService authService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
		// 인가(접근권한) 설정
		
		// 사이트 위변조 요청 방지
//		http
//		.csrf().disable();		
		// 로그인 설정
				
		// 로그아웃 설정
				
		// 사용자 인증 처리 컴포넌트 서비스 등록
		http.userDetailsService(authService);
		return http.build();
	} 
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
	}
	
	
	
	
}
