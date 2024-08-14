package com.Commu_back.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.controller.UserController;
import com.Commu_back.jwt.TokenProvider;
import com.Commu_back.mapper.UserMapper;
import com.Commu_back.vo.AuthVO;
import com.Commu_back.vo.TokenVO;
import com.Commu_back.vo.UserVO;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AuthService implements UserDetailsService{
	@Autowired
	private AuthenticationManagerBuilder managerBuilder;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenProvider tokenProvider;
	
	//가입하지 않은 계정으로 로그인 시도 시
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO userVo = userMapper.selectUserInfo(userId);
		if(userVo == null) throw new UsernameNotFoundException("User Not Found"+ userId);
		return userVo;
	}
	//6.유저 추가
		@Transactional(rollbackFor = Exception.class)
		public int setUser(UserVO userVo) {
			String pw = userVo.getUserPw();
			pw = passwordEncoder.encode(pw);
			userVo.setUserPw(pw);
			return userMapper.insertUser(userVo);
	}

    public TokenVO login(UserVO userVO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userVO.getUserId(),userVO.getUserPw());
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenVO(authentication);
    }
    
  	
}
