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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Commu_back.service.AuthService;
import com.Commu_back.vo.UserVO;

@Configuration
@EnableWebSecurity
public class WebSecurityCongfig{
	@Autowired
	private AuthService authService;
		
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {
//		httpSecurity.csrf().disable()
//		.authorizeRequests()
		//security 대상에서 제외
//		antMatchers("/login","/join","/api/v1/**","/resources/static/*/**","/error").permitAll()
		//해당 URL에 진입하기 위해서 Authentication(인증, 로그인)이 필요함
//		.antMatchers("front").authenticated()
		//해당 URL에 진입하기 위해서 Authorization(인가, ex)권한이 ADMIN인 유저만 진입 가능)이 필요함		
//      .antMatchers("back").hasAuthority()
//		httpSecurity
//		.formLogin().loginPage("/login")
//		.usernameParameter("userId")
//		.passwordParameter("userPassword")
//		.defaultSuccessUrl("/home"); //로그인 성공시 이동할 경로
//		httpSecurity
//		.logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 경로
//		.deleteCookies("JSESSIONID") //쿠키 제거
//      .invalidateHttpSession(true) //로그아웃시 세션 제거
//      .clearAuthentication(true) //권한정보 제거
//      .permitAll();
//		http
//		.exceptionHandling()
//		.accessDeniedPage("/access-denied"); //권한없는유저가 요청시 접속할 경로				
		// 사용자 인증 처리 컴포넌트 서비스 등록
		httpSecurity.userDetailsService(authService);
		return httpSecurity.build();
	} 
	
	// static 추가
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
	}
	
	
	
	
	
}
