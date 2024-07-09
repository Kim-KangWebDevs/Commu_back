package com.Commu_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Commu_back.mapper.UserMapper;
import com.Commu_back.vo.UserVO;

@Service
public class AuthService implements UserDetailsService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO userVo = userMapper.selectUserInfo(userId);
		if(userVo == null) throw new UsernameNotFoundException("User Not Found"+ userId);
		return userVo;
	}
	



}
