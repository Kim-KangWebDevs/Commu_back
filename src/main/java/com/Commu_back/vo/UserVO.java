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
	
	
	// 사용자 권한 목록 - 로그인한 계정이 갖는 권한 목록 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authList;
	}
	
	// 계정 비밀번호
	@Override
	public String getPassword() {
		return this.userPw;
	}
	
	//계정 Id
	@Override
	public String getUsername() {
		return this.userId;
	}
	
	// 계정 만료 여부 
	// true - 만료 안됨, false - 만료(로그인 불가)	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정 잠김 여부 
	// true - 잠김 안됨, false - 잠김(로그인 불가)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	// 계정 비밀번호 만료 여부
	// true - 만료 안됨, false - 만료(로그인 불가)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정 활성화 여부
	// true - 활성화, false - 비활성화(로그인 불가)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
