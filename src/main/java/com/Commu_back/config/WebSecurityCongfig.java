package com.Commu_back.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.Commu_back.jwt.JwtAccessDeniedHandler;
import com.Commu_back.jwt.JwtAuthenticationEntryPoint;
import com.Commu_back.jwt.JwtFilter;
import com.Commu_back.jwt.TokenProvider;
//import com.Commu_back.jwt.TokenProvider;
import com.Commu_back.service.AuthService;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class WebSecurityCongfig{
	@Autowired
	private AuthService authService;
//	private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider provider;
		
	// static 추가
	//암호화
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {
		httpSecurity
		//httpBasic를사용x
        .httpBasic(httpBasic->httpBasic
        .disable()
        )
        //csrf를사용x
        .csrf(csrf->csrf
        .disable()
        )
        //세션을사용x
        .sessionManagement(sessionManagement->sessionManagement
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        httpSecurity
        .exceptionHandling(exceptionHandling->exceptionHandling
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .accessDeniedHandler(jwtAccessDeniedHandler)
        );
        httpSecurity
        .authorizeHttpRequests((authz) -> authz
        //login과 조인을제외하고는 모드 토큰필요
        .requestMatchers("/login","/join","/test/TESTlogin").permitAll()
        .requestMatchers("/admin/**").hasAnyAuthority("role_admin")
        .requestMatchers("/user/**","/board/**","/reply/**").hasAnyAuthority("role_user")
        .anyRequest().authenticated()//
         );
        httpSecurity
        // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig class 적용
        .addFilterBefore(
        new JwtFilter(provider),
        UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();		

	}

	
		
}
