package com.Commu_back.vo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO implements UserDetails{
	private int userNo;
	private String userId;
	private String userPw;
	private String userChr;	//유저닉네임
	private String userEmail;
	private String userRegdate; //유저 가입날짜
	private String fileName;
	private int roleNo;
	private String roleName;
	private List<AuthVO> authList;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authList;
	}

	@Override
	public String getPassword() {
		return this.userPw;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}
		
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
